package cegepst.domain;

public class Card {
    public Rank rank;
    public Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "[" + rank.toSymbol() + " " + suit.toEmoji() + "]";
    }

    public int getRank() {
        return rank.toNumeric();
    }
}