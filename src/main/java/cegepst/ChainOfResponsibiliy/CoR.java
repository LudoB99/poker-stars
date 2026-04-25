package cegepst.ChainOfResponsibiliy;

import cegepst.Entities.Card;
import cegepst.Entities.Player;


import java.util.ArrayList;
import java.util.List;

public abstract class CoR {
    private CoR next;

    public CoR(CoR next) {
        this.next = next;
    }

    protected abstract boolean process(ArrayList<Card> cards);

    public boolean check(Player player) {
        if (process(player.getHole())) {
            player.setPlayerHand(player.getValidCards(), this.getClass().getSimpleName());
            return true;
        }
        reset(player.getHole());
        if (next != null) {
            return next.check(player);
        }
        return false;
    }

    private void reset(List<Card> cards) {
        for(Card card : cards) {
            card.setInHand(false);
        }
    }
}
