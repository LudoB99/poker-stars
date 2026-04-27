package cegepst.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cegepst.api.dto.CardDto;
import cegepst.api.dto.GameStateResponse;
import cegepst.api.model.GamePhase;
import cegepst.api.model.GameState;
import cegepst.domain.Card;
import cegepst.domain.Suit;
import cegepst.validation.EvaluatedHand;
import cegepst.validation.HandEvaluator;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final Map<String, GameState> sessions = new ConcurrentHashMap<>();

    public GameStateResponse startNewGame(HttpSession session) {
        GameState existing = sessions.get(session.getId());
        boolean hasChips = existing != null && existing.getHumanChips() >= GameState.BLIND
                && existing.getBotChips() >= GameState.BLIND;

        int humanChips = hasChips ? existing.getHumanChips() : GameState.STARTING_CHIPS;
        int botChips = hasChips ? existing.getBotChips() : GameState.STARTING_CHIPS;

        GameState state = new GameState(humanChips, botChips);
        state.startRound();
        sessions.put(session.getId(), state);
        return buildResponse(state);
    }

    public GameStateResponse processAction(String action, HttpSession session) {
        GameState state = sessions.get(session.getId());
        if (state == null || state.isGameOver()) {
            throw new IllegalStateException("Aucune partie en cours");
        }
        switch (action.toLowerCase()) {
        case "fold" -> handleFold(state);
        case "check" -> handleCheck(state);
        case "raise" -> handleRaise(state);
        default -> throw new IllegalArgumentException("Action inconnue: " + action);
        }
        return buildResponse(state);
    }

    private void handleFold(GameState state) {
        state.setBotChips(state.getBotChips() + state.getPot());
        state.setResult("LOSE");
        state.setGameOver(true);
    }

    private void handleCheck(GameState state) {
        advancePhase(state);
    }

    private void handleRaise(GameState state) {
        int amount = Math.min(GameState.RAISE_AMOUNT, Math.min(state.getHumanChips(), state.getBotChips()));
        if (amount > 0) {
            state.setHumanChips(state.getHumanChips() - amount);
            state.setBotChips(state.getBotChips() - amount);
            state.setPot(state.getPot() + amount * 2);
        }
        advancePhase(state);
    }

    private void advancePhase(GameState state) {
        state.setPhase(state.nextPhase());
        if (state.getPhase() == GamePhase.SHOWDOWN) {
            resolveShowdown(state);
        }
    }

    private void resolveShowdown(GameState state) {
        List<Card> humanCards = new ArrayList<>(state.getHumanPlayer().getHole());
        humanCards.addAll(state.getDealer().getCommunity());

        List<Card> botCards = new ArrayList<>(state.getBotPlayer().getHole());
        botCards.addAll(state.getDealer().getCommunity());

        EvaluatedHand humanHand = HandEvaluator.evaluate(humanCards);
        EvaluatedHand botHand = HandEvaluator.evaluate(botCards);

        state.setHumanHandName(humanHand.type().getDisplayName());
        state.setBotHandName(botHand.type().getDisplayName());
        state.setHumanBestHand(humanHand.cards());
        state.setBotBestHand(botHand.cards());

        int cmp = humanHand.compareTo(botHand);
        if (cmp > 0) {
            state.setHumanChips(state.getHumanChips() + state.getPot());
            state.setResult("WIN");
        } else if (cmp < 0) {
            state.setBotChips(state.getBotChips() + state.getPot());
            state.setResult("LOSE");
        } else {
            int half = state.getPot() / 2;
            state.setHumanChips(state.getHumanChips() + half);
            state.setBotChips(state.getBotChips() + half + state.getPot() % 2);
            state.setResult("TIE");
        }
        state.setGameOver(true);
    }

    private GameStateResponse buildResponse(GameState state) {
        List<Card> humanBest = state.getHumanBestHand();
        List<Card> botBest = state.getBotBestHand();

        List<Card> communityBest = new ArrayList<>(humanBest);
        communityBest.addAll(botBest);

        List<CardDto> humanCards = state.getHumanPlayer().getHole().stream().map(c -> toCardDto(c, humanBest)).toList();

        List<CardDto> botCards = state.getPhase() == GamePhase.SHOWDOWN
                ? state.getBotPlayer().getHole().stream().map(c -> toCardDto(c, botBest)).toList()
                : List.of(hiddenCard(), hiddenCard());

        List<CardDto> communityCards = state.visibleCommunityCards().stream().map(c -> toCardDto(c, communityBest))
                .toList();

        String phaseDisplay = switch (state.getPhase()) {
        case PRE_FLOP -> "Pré-Flop";
        case FLOP -> "Flop";
        case TURN -> "Turn";
        case RIVER -> "River";
        case SHOWDOWN -> "Abattage";
        };

        List<String> actions = state.isGameOver() ? List.of("new_game") : List.of("fold", "check", "raise");

        return new GameStateResponse(state.getPhase().name(), phaseDisplay, humanCards, botCards, communityCards,
                state.getPot(), state.getHumanChips(), state.getBotChips(), state.getHumanHandName(),
                state.getBotHandName(), state.getResult(), state.isGameOver(), actions);
    }

    private CardDto toCardDto(Card card, List<Card> bestHand) {
        String color = (card.suit == Suit.HEARTS || card.suit == Suit.DIAMONDS) ? "red" : "black";
        String suit = switch (card.suit) {
        case HEARTS -> "♥";
        case DIAMONDS -> "♦";
        case CLUBS -> "♣";
        case SPADES -> "♠";
        };
        boolean highlight = bestHand.stream().anyMatch(c -> c.rank == card.rank && c.suit == card.suit);
        return new CardDto(card.rank.toSymbol(), suit, color, false, highlight);
    }

    private CardDto hiddenCard() {
        return new CardDto("", "", "black", true, false);
    }
}
