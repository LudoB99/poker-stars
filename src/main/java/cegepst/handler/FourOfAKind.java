package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class FourOfAKind extends CoR {

    public FourOfAKind(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isFourOfAKind(cards);
    }
}
