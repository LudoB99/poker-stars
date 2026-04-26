package cegepst;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cegepst.domain.Card;
import cegepst.entities.Dealer;
import cegepst.entities.Player;
import cegepst.ui.Printer;
import cegepst.validation.EvaluatedHand;
import cegepst.validation.HandEvaluator;
import cegepst.validation.HandValidator;

public class Game {
    private Scanner input = new Scanner(System.in);
    private HandValidator evaluator = new HandEvaluator();
    private Dealer dealer = new Dealer();
    private Player player = new Player("VOUS");
    private Player opponent = new Player("ADVERSAIRE");

    public void start() {
        Printer.printWelcome();
        while (true) {
            playRound();
            if (!wantToContinue()) {
                break;
            }
        }
        Printer.printEndMessage();
    }

    private void playRound() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        players.add(opponent);
        dealer.deal(players);

        Printer.printHoleCards(player.name, player.getHole());
        if (playerFolds("Continuer? (o/n): ")) {
            System.out.println(opponent.name + " gagne!");
            return;
        }

        System.out.println("\n=== FLOP ===");
        Printer.printCommunityCards(dealer.getCommunity(), dealer.getFlopSize());
        if (playerFolds("Continuer? (o/n): ")) {
            System.out.println(opponent.name + " gagne!");
            return;
        }

        System.out.println("\n=== TURN ===");
        Printer.printCommunityCards(dealer.getCommunity(), dealer.getTurnSize());
        if (playerFolds("Continuer? (o/n): ")) {
            System.out.println(opponent.name + " gagne!");
            return;
        }

        System.out.println("\n=== RIVER ===");
        Printer.printCommunityCards(dealer.getCommunity(), dealer.getRiverSize());

        determineAndShowWinner();

        player.reset();
        opponent.reset();
        dealer.reset();
    }

    private void determineAndShowWinner() {
        System.out.println("\n=== ABATTAGE ===");

        List<Card> playerCards = new ArrayList<>(player.getHole());
        playerCards.addAll(dealer.getCommunity());

        List<Card> opponentCards = new ArrayList<>(opponent.getHole());
        opponentCards.addAll(dealer.getCommunity());

        EvaluatedHand playerHand = evaluator.evaluateBest(playerCards);
        EvaluatedHand opponentHand = evaluator.evaluateBest(opponentCards);

        System.out.println(player.name + " cartes: " + formatCards(player.getHole()));
        System.out.println(player.name + " main: " + playerHand.type().getDisplayName());

        System.out.println(opponent.name + " cartes: " + formatCards(opponent.getHole()));
        System.out.println(opponent.name + " main: " + opponentHand.type().getDisplayName());

        int comparison = playerHand.compareTo(opponentHand);
        if (comparison > 0) {
            System.out.println("\n🎉 " + player.name + " gagne! 🎉");
        } else if (comparison < 0) {
            System.out.println("\n🎉 " + opponent.name + " gagne! 🎉");
        } else {
            System.out.println("\n🤝 Égalité! 🤝");
        }
    }

    private boolean playerFolds(String prompt) {
        System.out.print(prompt);
        return !input.next().toLowerCase().startsWith("o");
    }

    private boolean wantToContinue() {
        System.out.print("\nJouer une autre main? (o/n): ");
        return input.next().toLowerCase().startsWith("o");
    }

    private String formatCards(List<Card> cards) {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(" ");
        }
        return sb.toString().trim();
    }
}
