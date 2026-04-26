package cegepst.ui;

import java.util.List;

import cegepst.domain.Card;

public class Printer {

    public static void printWelcome() {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║    Bienvenue à Poker Stars 🎰      ║");
        System.out.println("║     Édition Texas Hold'em          ║");
        System.out.println("╚════════════════════════════════════╝");
    }

    public static void printHoleCards(String playerName, List<Card> hole) {
        System.out.println("\n=== VOS CARTES ===");
        System.out.print(playerName + " : ");
        printCards(hole);
    }

    public static void printCommunityCards(List<Card> community, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(community.get(i));
        }
        System.out.println();
    }

    public static void printCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.print(card);
        }
        System.out.println();
    }

    public static void printEndMessage() {
        System.out.println("\nMerci d'avoir joué!");
    }
}
