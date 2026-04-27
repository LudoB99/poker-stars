package cegepst.api.model;

import java.util.List;

import cegepst.domain.Card;
import cegepst.entities.Dealer;
import cegepst.entities.Player;



public class GameState {
    public static final int STARTING_CHIPS = 1000;
    public static final int BLIND = 10;
    public static final int RAISE_AMOUNT = 20;

    private GamePhase phase;
    private final Player humanPlayer;
    private final Player botPlayer;
    private final Dealer dealer;
    private int pot;
    private int humanChips;
    private int botChips;
    private String result;
    private String humanHandName;
    private String botHandName;
    private boolean gameOver;
    private List<Card> humanBestHand = List.of();
    private List<Card> botBestHand = List.of();

    public GameState(int humanChips, int botChips) {
        this.humanPlayer = new Player("Vous");
        this.botPlayer = new Player("Bot");
        this.dealer = new Dealer();
        this.humanChips = humanChips;
        this.botChips = botChips;
    }

    public void startRound() {
        dealer.deal(List.of(humanPlayer, botPlayer));
        int blind = Math.min(BLIND, Math.min(humanChips, botChips));
        humanChips -= blind;
        botChips -= blind;
        pot = blind * 2;
        phase = GamePhase.PRE_FLOP;
        result = null;
        humanHandName = null;
        botHandName = null;
        gameOver = false;
        humanBestHand = List.of();
        botBestHand = List.of();
    }

    public List<Card> visibleCommunityCards() {
        int count = switch (phase) {
        case PRE_FLOP -> 0;
        case FLOP -> 3;
        case TURN -> 4;
        case RIVER, SHOWDOWN -> 5;
        };
        return dealer.getCommunity().subList(0, count);
    }

    public GamePhase nextPhase() {
        return switch (phase) {
        case PRE_FLOP -> GamePhase.FLOP;
        case FLOP -> GamePhase.TURN;
        case TURN -> GamePhase.RIVER;
        case RIVER -> GamePhase.SHOWDOWN;
        case SHOWDOWN -> GamePhase.SHOWDOWN;
        };
    }

    public GamePhase getPhase() {
        return phase;
    }

    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Player getBotPlayer() {
        return botPlayer;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getHumanChips() {
        return humanChips;
    }

    public void setHumanChips(int humanChips) {
        this.humanChips = humanChips;
    }

    public int getBotChips() {
        return botChips;
    }

    public void setBotChips(int botChips) {
        this.botChips = botChips;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getHumanHandName() {
        return humanHandName;
    }

    public void setHumanHandName(String name) {
        this.humanHandName = name;
    }

    public String getBotHandName() {
        return botHandName;
    }

    public void setBotHandName(String name) {
        this.botHandName = name;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Card> getHumanBestHand() {
        return humanBestHand;
    }

    public void setHumanBestHand(List<Card> humanBestHand) {
        this.humanBestHand = humanBestHand;
    }

    public List<Card> getBotBestHand() {
        return botBestHand;
    }

    public void setBotBestHand(List<Card> botBestHand) {
        this.botBestHand = botBestHand;
    }
}
