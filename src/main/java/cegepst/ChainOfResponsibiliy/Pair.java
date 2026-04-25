package cegepst.ChainOfResponsibiliy;
import cegepst.Entities.Card;
import cegepst.Entities.Validator;

import java.util.ArrayList;

public class Pair extends CoR {

    public Pair(CoR next){
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isOnePair(cards);
    }
}
