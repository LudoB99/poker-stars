package cegepst.domain;

public enum HandType {
    ROYAL_FLUSH,
    STRAIGHT_FLUSH,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    FLUSH,
    STRAIGHT,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD;

    public int getRank() {
        return switch (this) {
            case ROYAL_FLUSH -> 10;
            case STRAIGHT_FLUSH -> 9;
            case FOUR_OF_A_KIND -> 8;
            case FULL_HOUSE -> 7;
            case FLUSH -> 6;
            case STRAIGHT -> 5;
            case THREE_OF_A_KIND -> 4;
            case TWO_PAIR -> 3;
            case ONE_PAIR -> 2;
            case HIGH_CARD -> 1;
        };
    }

    public String getDisplayName() {
        return switch (this) {
            case ROYAL_FLUSH -> "Quinte Royale";
            case STRAIGHT_FLUSH -> "Quinte Flush";
            case FOUR_OF_A_KIND -> "Carré";
            case FULL_HOUSE -> "Full";
            case FLUSH -> "Flush";
            case STRAIGHT -> "Quinte";
            case THREE_OF_A_KIND -> "Brelan";
            case TWO_PAIR -> "Deux Paires";
            case ONE_PAIR -> "Une Paire";
            case HIGH_CARD -> "Hauteur";
        };
    }
}

