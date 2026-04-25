package cegepst.ChainOfResponsibiliy;
import cegepst.Entities.Card;
import cegepst.Entities.Validator;

import java.util.ArrayList;

public class HighCard extends CoR {

    public HighCard(CoR next){
        super(next);
    }

    @Override
    public boolean process(ArrayList<Card> cards) {
        Validator.setHighCard(cards);
        return true;
    }
}
