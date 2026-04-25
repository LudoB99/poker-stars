package cegepst.Entities;

public class Card {
    private Rank rank;
    private Suit suit;
    private boolean isInHand;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isInHand() {
        return isInHand;
    }

    public void setInHand(boolean inHand) {
        isInHand = inHand;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getCardName() {
        return "[" + rank.getRank() + ' ' + suit.getSuit() + "] ";
    }

    public int getCardRank() {
        if(rank.equals(Rank.JACK.getRank())) {return 11;}
        if(rank.equals(Rank.QUEEN.getRank())) {return 12;}
        if(rank.equals(Rank.KING.getRank())) {return 13;}
        if(rank.equals(Rank.ACE.getRank())) {return 14;}
        return rank.getValue();
    }
}
