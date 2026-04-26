package cegepst.entities;

import java.util.ArrayList;
import java.util.Collections;

import cegepst.domain.Card;
import cegepst.domain.Rank;
import cegepst.domain.Suit;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        reset();
    }

    public Card draw(int i) {
        if (i >= cards.size()) {
            return null;
        }
        return cards.remove(i);
    }

    public void reset() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void showDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }
}
