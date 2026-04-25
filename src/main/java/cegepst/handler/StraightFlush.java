package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class StraightFlush extends CoR {

    public StraightFlush(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isStraightFlush(cards);
    }
}
