package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class ThreeOfAKind extends CoR {

    public ThreeOfAKind(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isThreeOfAKind(cards);
    }
}
