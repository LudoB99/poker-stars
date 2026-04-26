package cegepst.validation;

import java.util.List;

import cegepst.domain.Card;

public interface HandValidator {

    /**
     * Evaluates the best possible 5-card poker hand from 7 cards.
     *
     * @param sevenCards
     *            the hole cards (2) and community cards (5)
     *
     * @return the best hand found
     *
     * @throws IllegalArgumentException
     *             if not exactly 7 cards provided
     */
    EvaluatedHand evaluateBest(List<Card> sevenCards);
}
