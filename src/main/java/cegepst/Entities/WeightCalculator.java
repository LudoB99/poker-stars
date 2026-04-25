package cegepst.Entities;

import java.util.ArrayList;
import java.util.List;

public class WeightCalculator {
    ArrayList<Card> hand;
    String name;

    public WeightCalculator(ArrayList<Card> hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    public int getWeight() {
        return Name.valueOf(name.toUpperCase()).getRank() * getHighestCardRank(hand);
    }

    public int getHighestCardRank(ArrayList<Card> cards) {
        List<Card> sortedCards = Sorter.sortByRank(cards);
        return sortedCards.get(sortedCards.size() - 1).getCardRank();
    }
}
