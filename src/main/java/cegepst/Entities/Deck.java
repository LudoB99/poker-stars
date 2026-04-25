package cegepst.Entities;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private static Deck instance = null;

    public Deck() {
        cards = new ArrayList<>();
        getNewDeck();
    }

    public Card draw(int i) {
        Card card = cards.get(i);
        cards.remove(i);
        return card;
    }

    public void getNewDeck() {
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void showDeck() {
        for(Card card : cards) {
            System.out.println(card.getCardName());
        }
    }

    public void sort() {
        cards.clear();
        getNewDeck();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getDeckSize() {
        return cards.size();
    }
}
