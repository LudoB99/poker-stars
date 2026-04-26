package cegepst.validation;

import java.util.List;

import cegepst.domain.Card;

public interface HandValidator {
    EvaluatedHand evaluateBest(List<Card> sevenCards);
}
