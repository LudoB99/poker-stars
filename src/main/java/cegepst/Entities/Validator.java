package cegepst.Entities;

import java.util.Arrays;
import java.util.List;

public class Validator {

    public static boolean isFlush(List<Card> cards) { //TODO: Test
        cards = Sorter.sortBySuit(cards);
        for (Card card : cards) {
            card.setInHand(true);
            int counter = 0;
            for (Card card2 : cards) {
                if (card.getSuit() == card2.getSuit() && card.getRank() != card2.getRank()) {
                    card2.setInHand(true);
                    ++counter;
                }

                if (counter == 4){
                    return true;
                }
            }
            card.setInHand(false);
        }
        return false;
    }

    public static boolean isStraightFlush(List<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    public static boolean isTwoPairs(List<Card> cards) {
        cards = Sorter.sortByRank(cards);
        int counter = 0;
        for (int i = 0; i < cards.size(); ++i) {
            if (i + 1 == cards.size()) {
                return false;
            }
            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                cards.get(i).setInHand(true);
                cards.get(i+1).setInHand(true);
                ++counter;
            }
            if (counter == 2) return true;
        }
        return false;
    }

    public static boolean isOnePair(List<Card> cards) {
        cards = Sorter.sortByRank(cards);
        for (int i = 0; i < cards.size(); ++i) {
            if (i + 1 == cards.size()) return false;
            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                cards.get(i).setInHand(true);
                cards.get(i+1).setInHand(true);
                return true;
            }
        }
        return false;
    }

    public static boolean isThreeOfAKind(List<Card> cards) {
        cards = Sorter.sortByRank(cards);
        for (Card card : cards) {
            int counter = 0;
            card.setInHand(true);
            for (Card card2 : cards) {
                if (card.getRank() == card2.getRank() && card.getSuit() != card2.getSuit()) {
                    card2.setInHand(true);
                    ++counter;
                }

                if (counter == 2) return true;
            }
            reset(cards);
        }
        return false;
    }

    public static boolean isFullHouse(List<Card> cards) {
        boolean threeSameCards = false;
        int twoSameCardValue = 0;
        boolean twoSameCards = false;
        cards = Sorter.sortByRank(cards);
        for (Card card : cards) {
            int counter = 0;
            card.setInHand(true);
            for (Card card2 : cards) {
                if (card.getRank() == card2.getRank() && card.getSuit() != card2.getSuit()) {
                    card2.setInHand(true);
                    counter++;
                    if (counter == 2) {
                        threeSameCards = true; twoSameCards = false; twoSameCardValue = 0;
                    } else if (counter == 1) {
                        twoSameCards = true;
                        if (card.getCardRank() > twoSameCardValue && ! threeSameCards) {
                            twoSameCardValue = card.getCardRank();
                        }
                    }
                }
            }
            reset(cards);
        }

        return twoSameCards && threeSameCards;
    }

    public static boolean isRoyalFlush(List<Card> cards) {
        return isFlush(cards) && isStraight(cards)  && cards.get(cards.size() - 1).getRank() == Rank.ACE;
    }

    public static boolean isFourOfAKind(List<Card> cards) {
        for (Card card : cards) {
            int counter = 0;
            card.setInHand(true);
            for (Card card2 : cards) {
                if (card.getRank() == card2.getRank() && card.getSuit() != card2.getSuit()) {
                    card2.setInHand(true);
                    ++counter;
                }
                if (counter == 3) return true;
            }
            reset(cards);
        }
        return false;
    }

    public static boolean isStraight(List<Card> cards) {
        cards = Sorter.sortByRank(cards);
        int counter = 0;
        for (int i = 0; i < cards.size(); ++i) {
            if (i + 1 == cards.size()) {
                return false;
            }

            if (cards.get(i + 1).getRank().getValue() - cards.get(i).getRank().getValue() == 1) {
                cards.get(i).setInHand(true);
                cards.get(i + 1).setInHand(true);
                ++counter;
            } else {
                counter = 0;
            }

            if (counter == 4) {
                return true;
            }
        }
        return false;
    }

    public static void setHighCard(List<Card> cards) {
        cards = Sorter.sortByRank(cards);
        cards.get(cards.size() - 1).setInHand(true);
    }

    private static void reset(List<Card> cards) {
        for(Card card : cards ){
            card.setInHand(false);
        }
    }
}
