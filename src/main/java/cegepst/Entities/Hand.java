package cegepst.Entities;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;
    private int weight;
    private String namedType;

    public Hand(ArrayList<Card> hand, int weight, String namedType) {
        this.hand = hand;
        this.weight = weight;
        this.namedType = namedType;
    }

    public void reset() {
        hand.clear();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getWeight() {
        return weight;
    }

    public String getNamedType() {
        return namedType;
    }
}
