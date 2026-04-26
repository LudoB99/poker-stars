package cegepst.validation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cegepst.domain.Card;
import cegepst.domain.HandType;
import cegepst.domain.Suit;

/**
 * Standard Texas Hold'em hand evaluation.
 *
 * Evaluates 5-card poker hands and finds the best hand from 7 cards.
 *
 * Algorithm:
 * 1. Generate all C(7,5) = 21 possible 5-card combinations from 7 cards
 * 2. Evaluate each combination against hand types (Royal Flush → High Card)
 * 3. Return the best hand found
 */
public class HandEvaluator implements HandValidator {

    private static final HandEvaluator INSTANCE = new HandEvaluator();

    /**
     * Singleton factory.
     * Since this is stateless, we can reuse the same instance.
     */
    public static HandEvaluator getInstance() {
        return INSTANCE;
    }

    /**
     * Static convenience method for direct evaluation without needing the instance.
     * Useful for quick one-off evaluations or in test code.
     *
     * @param sevenCards the 7 cards to evaluate
     * @return the best hand found
     */
    public static EvaluatedHand evaluate(List<Card> sevenCards) {
        return INSTANCE.evaluateBest(sevenCards);
    }

    /**
     * Instance method implementing HandValidator interface.
     * Finds the best 5-card poker hand from 7 cards.
     *
     * @param sevenCards the 7 cards to evaluate (typically 2 hole + 5 community)
     * @return the best hand found
     */
    @Override
    public EvaluatedHand evaluateBest(List<Card> sevenCards) {
        return doEvaluate(sevenCards);
    }


    /**
     * Internal evaluation logic shared by static and instance methods.
     */
    private EvaluatedHand doEvaluate(List<Card> sevenCards) {
        if (sevenCards == null || sevenCards.size() != 7) {
            throw new IllegalArgumentException("Must evaluate exactly 7 cards");
        }

        // Generate all possible 5-card combinations
        List<List<Card>> combinations = generate5CardCombinations(sevenCards);

        // Evaluate each, track the best
        EvaluatedHand best = null;
        for (List<Card> combo : combinations) {
            EvaluatedHand evaluated = evaluateFiveCardHand(combo);
            if (best == null || evaluated.compareTo(best) > 0) {
                best = evaluated;
            }
        }

        return best;
    }

    /**
     * Evaluates a single 5-card hand.
     * Tries hand types from best to worst and returns on first match.
     *
     * @param fiveCards exactly 5 cards
     * @return the evaluated hand
     */
    private static EvaluatedHand evaluateFiveCardHand(List<Card> fiveCards) {
        // Sort by rank descending for easier pattern matching
        List<Card> sorted = new ArrayList<>(fiveCards);
        sorted.sort(Comparator.comparingInt(Card::getRank).reversed());

        Map<Integer, Integer> rankCounts = countRanks(sorted);

        // Royal Flush: A-K-Q-J-10, all same suit
        if (isRoyalFlush(sorted)) {
            return new EvaluatedHand(
                HandType.ROYAL_FLUSH,
                sorted,
                new int[] { 14 } // Ace high
            );
        }

        // Straight Flush: 5 consecutive cards, same suit
        if (isStraightFlush(sorted)) {
            int high = sorted.get(0).getRank();
            return new EvaluatedHand(
                HandType.STRAIGHT_FLUSH,
                sorted,
                new int[] { high }
            );
        }

        // Four of a Kind: 4 cards of one rank + 1 kicker
        if (rankCounts.containsValue(4)) {
            int quad = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 4)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(0);
            int kicker = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .max(Integer::compare)
                .orElse(0);
            return new EvaluatedHand(
                HandType.FOUR_OF_A_KIND,
                sorted,
                new int[] { quad, kicker }
            );
        }

        // Full House: 3 cards of one rank + 2 cards of another
        if (rankCounts.containsValue(3) && rankCounts.containsValue(2)) {
            int trips = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(0);
            int pair = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(0);
            return new EvaluatedHand(
                HandType.FULL_HOUSE,
                sorted,
                new int[] { trips, pair }
            );
        }

        // Flush: all 5 cards same suit
        if (isFlush(sorted)) {
            int[] kickers = sorted.stream()
                .mapToInt(Card::getRank)
                .toArray();
            return new EvaluatedHand(
                HandType.FLUSH,
                sorted,
                kickers
            );
        }

        // Straight: 5 consecutive cards
        if (isStraight(sorted)) {
            int high = sorted.get(0).getRank();
            return new EvaluatedHand(
                HandType.STRAIGHT,
                sorted,
                new int[] { high }
            );
        }

        // Three of a Kind: 3 cards of one rank + 2 kickers
        if (rankCounts.containsValue(3)) {
            int trips = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(0);
            int[] kickers = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
            int[] result = new int[kickers.length + 1];
            result[0] = trips;
            System.arraycopy(kickers, 0, result, 1, kickers.length);
            return new EvaluatedHand(
                HandType.THREE_OF_A_KIND,
                sorted,
                result
            );
        }

        // Two Pair: 2 cards of one rank + 2 cards of another + 1 kicker
        List<Integer> pairs = rankCounts.entrySet().stream()
            .filter(e -> e.getValue() == 2)
            .map(Map.Entry::getKey)
            .sorted(Comparator.reverseOrder())
            .toList();
        if (pairs.size() == 2) {
            int kicker = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(0);
            return new EvaluatedHand(
                HandType.TWO_PAIR,
                sorted,
                new int[] { pairs.get(0), pairs.get(1), kicker }
            );
        }

        // One Pair: 2 cards of one rank + 3 kickers
        if (pairs.size() == 1) {
            int pair = pairs.get(0);
            int[] kickers = rankCounts.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
            int[] result = new int[kickers.length + 1];
            result[0] = pair;
            System.arraycopy(kickers, 0, result, 1, kickers.length);
            return new EvaluatedHand(
                HandType.ONE_PAIR,
                sorted,
                result
            );
        }

        // High Card: no combinations, just 5 kickers
        int[] kickers = sorted.stream()
            .mapToInt(Card::getRank)
            .toArray();
        return new EvaluatedHand(
            HandType.HIGH_CARD,
            sorted,
            kickers
        );
    }

    /**
     * Generates all C(7,5) = 21 possible 5-card combinations from 7 cards.
     */
    private static List<List<Card>> generate5CardCombinations(List<Card> sevenCards) {
        List<List<Card>> combinations = new ArrayList<>();
        int[] indices = new int[5];

        // Initialize indices: 0, 1, 2, 3, 4
        for (int i = 0; i < 5; i++) {
            indices[i] = i;
        }

        while (true) {
            // Add current combination
            List<Card> combo = new ArrayList<>();
            for (int idx : indices) {
                combo.add(sevenCards.get(idx));
            }
            combinations.add(combo);

            // Generate next combination
            int i = 4;
            while (i >= 0 && indices[i] == 6 - 4 + i) {
                i--;
            }

            if (i < 0) break; // No more combinations

            indices[i]++;
            for (int j = i + 1; j < 5; j++) {
                indices[j] = indices[j - 1] + 1;
            }
        }

        return combinations;
    }

    /**
     * Counts how many cards of each rank are present.
     * Returns map: rank value → count (e.g., 14 → 2 for two Aces)
     */
    private static Map<Integer, Integer> countRanks(List<Card> cards) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Card card : cards) {
            int rankValue = card.getRank();
            counts.put(rankValue, counts.getOrDefault(rankValue, 0) + 1);
        }
        return counts;
    }

    /**
     * Checks if all 5 cards are the same suit.
     */
    private static boolean isFlush(List<Card> cards) {
        Suit firstSuit = cards.get(0).suit;
        return cards.stream().allMatch(c -> c.suit == firstSuit);
    }

    /**
     * Checks if cards form a straight (5 consecutive ranks).
     * Assumes cards are sorted by rank descending.
     */
    private static boolean isStraight(List<Card> cards) {
        for (int i = 0; i < 4; i++) {
            int expected = cards.get(i).getRank() - 1;
            int actual = cards.get(i + 1).getRank();
            if (expected != actual) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if cards form a straight flush.
     */
    private static boolean isStraightFlush(List<Card> cards) {
        return isFlush(cards) && isStraight(cards);
    }

    /**
     * Checks if cards form a royal flush (A-K-Q-J-10 of same suit).
     */
    private static boolean isRoyalFlush(List<Card> cards) {
        if (!isFlush(cards) || !isStraight(cards)) {
            return false;
        }
        // Must start with Ace (14) when sorted descending
        return cards.get(0).getRank() == 14;
    }
}
