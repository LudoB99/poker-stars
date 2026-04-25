package cegepst.Entities;

import cegepst.Entities.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private ArrayList<Card> hole;
    private Hand hand;
    private String name;

    public Player(String name) {
        hole = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Card> getHole() {
        return hole;
    }

    public void receiveCard(Card card){
        hole.add(card);
    }

    public void reset() {
        if(hand != null) {
            hand.reset();
        }
        hole.clear();
    }

    public void setHole(ArrayList<Card> hole) {
        this.hole = hole;
    }

    public void setPlayerHand(ArrayList<Card> cards, String namedType) {
        this.hand = new Hand(new ArrayList<Card>(Sorter.sortByRank(cards)), new WeightCalculator(cards,
                namedType).getWeight(), Name.valueOf(namedType.toUpperCase()).getName());
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {return name;}

    public void showHole() {
        for(Card card : hole) {
            System.out.print(card.getCardName());
        }
        System.out.println();
    }

    public ArrayList<Card> getValidCards() {
        ArrayList<Card> result = new ArrayList<Card>();
        for(Card card : hole) {
            if(card.isInHand()){
                result.add(card);
            }
        }
        return result;
    }
}
