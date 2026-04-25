package cegepst.Entities;

public enum Suit {
    HEART('♥', 3),
    DIAMOND('♦', 2),
    CLUB('♣', 1),
    SPADE('♠', 4);

    private char suit;
    private int value;

    Suit(char suit, int rank) {
        this.suit = suit;
        this.value = rank;
    }

    public char getSuit() {
        return suit;
    }

    public int getValue() { return value; }
}