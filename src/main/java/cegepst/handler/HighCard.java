package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class HighCard extends CoR {

    public HighCard(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        Validator.setHighCard(cards);
        return true;
    }
}
