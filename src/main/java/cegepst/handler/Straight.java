package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class Straight extends CoR {

    public Straight(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isStraight(cards);
    }
}
