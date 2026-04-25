package cegepst.handler;

import java.util.ArrayList;

import cegepst.entities.Card;
import cegepst.entities.Validator;

public class FullHouse extends CoR {

    public FullHouse(CoR next) {
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isFullHouse(cards);
    }
}
