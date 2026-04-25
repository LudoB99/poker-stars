package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class Flush extends CoR {

    public Flush(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isFlush(cards);
    }
}
