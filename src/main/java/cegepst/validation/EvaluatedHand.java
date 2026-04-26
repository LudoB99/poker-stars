package cegepst.validation;

import java.util.List;

import cegepst.domain.Card;
import cegepst.domain.HandType;

public record EvaluatedHand(HandType type, List<Card> cards, int[] kickers) {

    public int compareTo(EvaluatedHand other) {
        int typeComparison = Integer.compare(other.type.getRank(), this.type.getRank());
        if (typeComparison != 0) {
            return typeComparison;
        }

        for (int i = 0; i < kickers.length && i < other.kickers.length; i++) {
            int kickerComparison = Integer.compare(other.kickers[i], this.kickers[i]);
            if (kickerComparison != 0) {
                return kickerComparison;
            }
        }

        return 0;
    }

    public boolean isTie(EvaluatedHand other) {
        return compareTo(other) == 0;
    }

    @Override
    public String toString() {
        return String.format("%s (cards: %s)", type.getDisplayName(), cards);
    }
}

