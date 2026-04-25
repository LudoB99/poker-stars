package cegepst.ChainOfResponsibiliy;
import cegepst.Entities.Card;
import cegepst.Entities.Validator;

import java.util.ArrayList;

public class FullHouse extends CoR {

    public FullHouse(CoR next){
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        return Validator.isFullHouse(cards);
    }
}
