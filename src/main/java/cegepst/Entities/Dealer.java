package cegepst.entities;

import java.util.ArrayList;
import java.util.List;

import cegepst.domain.Card;

public class Dealer {
    private static final int COMMUNITY_SIZE = 5;
    private static final int HOLE_SIZE = 2;
    private static final int FLOP_SIZE = 3;
    private static final int TURN_SIZE = 4;
    private static final int RIVER_SIZE = 5;
    private Deck deck;
    private ArrayList<Card> community = new ArrayList<>();

    public Dealer() {
        deck = new Deck();
    }

    public void reset() {
        community.clear();
        deck.reset();
        deck.shuffle();
    }

    public void deal(List<Player> players) {
        for (Player player : players) {
            player.reset();
        }

        reset();

        for (int i = 0; i < HOLE_SIZE; ++i) {
            for (Player player : players) {
                player.receiveCard(deck.draw(deck.size() - 1));
            }
        }

        for (int i = 0; i < COMMUNITY_SIZE; ++i) {
            community.add(deck.draw(deck.size() - 1));
        }
    }

    public ArrayList<Card> getCommunity() {
        return community;
    }

    public int getFlopSize() {
        return FLOP_SIZE;
    }

    public int getTurnSize() {
        return TURN_SIZE;
    }

    public int getRiverSize() {
        return RIVER_SIZE;
    }
}
