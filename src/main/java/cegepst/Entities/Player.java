package cegepst.entities;

import java.util.ArrayList;

import cegepst.domain.Card;

public class Player {
    private ArrayList<Card> hole = new ArrayList<>();
    public String name;

    public Player(String name) {
        this.name = name;
    }

    public void receiveCard(Card card) {
        hole.add(card);
    }

    public void reset() {
        hole.clear();
    }

    public ArrayList<Card> getHole() {
        return hole;
    }

    public void setHole(ArrayList<Card> hole) {
        this.hole = hole;
    }
}
