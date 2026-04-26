package cegepst.entities;

import org.junit.jupiter.api.Test;

import cegepst.domain.Card;
import cegepst.domain.HandType;
import cegepst.domain.Rank;
import cegepst.domain.Suit;
import cegepst.validation.EvaluatedHand;
import cegepst.validation.HandEvaluator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive tests for the HandEvaluator. Tests all hand types and tie-breaking logic.
 */
class HandEvaluatorTest {

    // Helper to create cards
    private Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    private List<Card> cards(Card... cards) {
        List<Card> list = new ArrayList<>();
        for (Card c : cards) {
            list.add(c);
        }
        return list;
    }

    // ==================== Hand Type Tests ====================

    @Test
    void testRoyalFlush() {
        // A♠ K♠ Q♠ J♠ 10♠ + 2 other cards
        List<Card> hand = cards(card(Rank.ACE, Suit.SPADES), card(Rank.KING, Suit.SPADES),
                card(Rank.QUEEN, Suit.SPADES), card(Rank.JACK, Suit.SPADES), card(Rank.TEN, Suit.SPADES),
                card(Rank.NINE, Suit.HEARTS), card(Rank.EIGHT, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.ROYAL_FLUSH, best.type());
    }

    @Test
    void testStraightFlush() {
        // 5♣ 6♣ 7♣ 8♣ 9♣
        List<Card> hand = cards(card(Rank.FIVE, Suit.CLUBS), card(Rank.SIX, Suit.CLUBS), card(Rank.SEVEN, Suit.CLUBS),
                card(Rank.EIGHT, Suit.CLUBS), card(Rank.NINE, Suit.CLUBS), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.STRAIGHT_FLUSH, best.type());
        assertEquals(9, best.kickers()[0]); // High card
    }

    @Test
    void testFourOfAKind() {
        // A♠ A♥ A♦ A♣ K♠
        List<Card> hand = cards(card(Rank.ACE, Suit.SPADES), card(Rank.ACE, Suit.HEARTS), card(Rank.ACE, Suit.DIAMONDS),
                card(Rank.ACE, Suit.CLUBS), card(Rank.KING, Suit.SPADES), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.FOUR_OF_A_KIND, best.type());
        assertEquals(14, best.kickers()[0]); // Quad Aces
        assertEquals(13, best.kickers()[1]); // King kicker
    }

    @Test
    void testFullHouse() {
        // K♠ K♥ K♦ 5♠ 5♥
        List<Card> hand = cards(card(Rank.KING, Suit.SPADES), card(Rank.KING, Suit.HEARTS),
                card(Rank.KING, Suit.DIAMONDS), card(Rank.FIVE, Suit.SPADES), card(Rank.FIVE, Suit.HEARTS),
                card(Rank.TWO, Suit.HEARTS), card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.FULL_HOUSE, best.type());
        assertEquals(13, best.kickers()[0]); // Kings
        assertEquals(5, best.kickers()[1]); // Fives
    }

    @Test
    void testFlush() {
        // A♦ K♦ Q♦ J♦ 9♦
        List<Card> hand = cards(card(Rank.ACE, Suit.DIAMONDS), card(Rank.KING, Suit.DIAMONDS),
                card(Rank.QUEEN, Suit.DIAMONDS), card(Rank.JACK, Suit.DIAMONDS), card(Rank.NINE, Suit.DIAMONDS),
                card(Rank.TWO, Suit.HEARTS), card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.FLUSH, best.type());
        assertEquals(14, best.kickers()[0]); // Ace high
    }

    @Test
    void testStraight() {
        // 9♠ 10♥ J♣ Q♦ K♠
        List<Card> hand = cards(card(Rank.NINE, Suit.SPADES), card(Rank.TEN, Suit.HEARTS), card(Rank.JACK, Suit.CLUBS),
                card(Rank.QUEEN, Suit.DIAMONDS), card(Rank.KING, Suit.SPADES), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.STRAIGHT, best.type());
        assertEquals(13, best.kickers()[0]); // King high
    }

    @Test
    void testThreeOfAKind() {
        // J♠ J♥ J♦ 9♠ 5♣
        List<Card> hand = cards(card(Rank.JACK, Suit.SPADES), card(Rank.JACK, Suit.HEARTS),
                card(Rank.JACK, Suit.DIAMONDS), card(Rank.NINE, Suit.SPADES), card(Rank.FIVE, Suit.CLUBS),
                card(Rank.TWO, Suit.HEARTS), card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.THREE_OF_A_KIND, best.type());
        assertEquals(11, best.kickers()[0]); // Trips Jacks
        assertEquals(9, best.kickers()[1]); // First kicker
        assertEquals(5, best.kickers()[2]); // Second kicker
    }

    @Test
    void testTwoPair() {
        // A♠ A♥ K♣ K♦ Q♠
        List<Card> hand = cards(card(Rank.ACE, Suit.SPADES), card(Rank.ACE, Suit.HEARTS), card(Rank.KING, Suit.CLUBS),
                card(Rank.KING, Suit.DIAMONDS), card(Rank.QUEEN, Suit.SPADES), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.TWO_PAIR, best.type());
        assertEquals(14, best.kickers()[0]); // Aces
        assertEquals(13, best.kickers()[1]); // Kings
        assertEquals(12, best.kickers()[2]); // Queen kicker
    }

    @Test
    void testOnePair() {
        // 7♠ 7♥ K♣ Q♦ J♠
        List<Card> hand = cards(card(Rank.SEVEN, Suit.SPADES), card(Rank.SEVEN, Suit.HEARTS),
                card(Rank.KING, Suit.CLUBS), card(Rank.QUEEN, Suit.DIAMONDS), card(Rank.JACK, Suit.SPADES),
                card(Rank.TWO, Suit.HEARTS), card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.ONE_PAIR, best.type());
        assertEquals(7, best.kickers()[0]); // Pair of Sevens
        assertEquals(13, best.kickers()[1]); // King
        assertEquals(12, best.kickers()[2]); // Queen
        assertEquals(11, best.kickers()[3]); // Jack
    }

    @Test
    void testHighCard() {
        // A♠ K♥ Q♣ J♦ 9♠
        List<Card> hand = cards(card(Rank.ACE, Suit.SPADES), card(Rank.KING, Suit.HEARTS), card(Rank.QUEEN, Suit.CLUBS),
                card(Rank.JACK, Suit.DIAMONDS), card(Rank.NINE, Suit.SPADES), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.HIGH_CARD, best.type());
        assertEquals(14, best.kickers()[0]); // Ace
        assertEquals(13, best.kickers()[1]); // King
    }

    // ==================== Tie-Breaking Tests ====================

    @Test
    void testPairTieBreaking() {
        // Pair of Kings with A, K, Q kickers beats Pair of Kings with A, Q, J
        List<Card> hand1 = cards(card(Rank.KING, Suit.SPADES), card(Rank.KING, Suit.HEARTS), card(Rank.ACE, Suit.CLUBS),
                card(Rank.QUEEN, Suit.DIAMONDS), card(Rank.JACK, Suit.SPADES), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        List<Card> hand2 = cards(card(Rank.KING, Suit.CLUBS), card(Rank.KING, Suit.DIAMONDS),
                card(Rank.ACE, Suit.SPADES), card(Rank.QUEEN, Suit.HEARTS), card(Rank.JACK, Suit.CLUBS),
                card(Rank.FIVE, Suit.HEARTS), card(Rank.FOUR, Suit.HEARTS));

        EvaluatedHand best1 = HandEvaluator.evaluate(hand1);
        EvaluatedHand best2 = HandEvaluator.evaluate(hand2);

        // Both are pair of Kings with A, Q, J
        assertTrue(best1.isTie(best2), "Should be equal hands");
    }

    @Test
    void testFlushTieBreaking() {
        // Flush with A-high beats flush with K-high
        List<Card> hand1 = cards(card(Rank.ACE, Suit.HEARTS), card(Rank.QUEEN, Suit.HEARTS),
                card(Rank.JACK, Suit.HEARTS), card(Rank.NINE, Suit.HEARTS), card(Rank.SEVEN, Suit.HEARTS),
                card(Rank.TWO, Suit.CLUBS), card(Rank.THREE, Suit.CLUBS));

        List<Card> hand2 = cards(card(Rank.KING, Suit.DIAMONDS), card(Rank.QUEEN, Suit.DIAMONDS),
                card(Rank.JACK, Suit.DIAMONDS), card(Rank.NINE, Suit.DIAMONDS), card(Rank.SEVEN, Suit.DIAMONDS),
                card(Rank.TWO, Suit.CLUBS), card(Rank.THREE, Suit.CLUBS));

        EvaluatedHand best1 = HandEvaluator.evaluate(hand1);
        EvaluatedHand best2 = HandEvaluator.evaluate(hand2);

        assertTrue(best1.compareTo(best2) > 0, "Ace-high flush should beat King-high flush");
    }

    // ==================== Best Hand from 7 Cards ====================

    @Test
    void testFindsBestHandFromSeven() {
        // Has both a flush and a pair, should find flush (higher ranked)
        // Cards chosen so no straight flush is possible (non-consecutive club ranks)
        List<Card> hand = cards(card(Rank.ACE, Suit.SPADES), card(Rank.ACE, Suit.HEARTS), // Pair of Aces
                card(Rank.TWO, Suit.CLUBS), card(Rank.FIVE, Suit.CLUBS), card(Rank.SEVEN, Suit.CLUBS),
                card(Rank.NINE, Suit.CLUBS), card(Rank.JACK, Suit.CLUBS) // Flush, no straight flush
        );

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.FLUSH, best.type());
    }

    @Test
    void testFindsFullHouseOverStraight() {
        // Can make both a straight and a full house
        List<Card> hand = cards(card(Rank.KING, Suit.SPADES), card(Rank.KING, Suit.HEARTS), card(Rank.KING, Suit.CLUBS), // Three
                                                                                                                         // Kings
                card(Rank.QUEEN, Suit.DIAMONDS), card(Rank.QUEEN, Suit.SPADES), // Pair of Queens
                card(Rank.JACK, Suit.HEARTS), card(Rank.TEN, Suit.CLUBS));

        EvaluatedHand best = HandEvaluator.evaluate(hand);
        assertEquals(HandType.FULL_HOUSE, best.type());
    }

    // ==================== Edge Cases ====================

    @Test
    void testRejectsWrongCardCount() {
        List<Card> tooFew = cards(card(Rank.ACE, Suit.SPADES), card(Rank.KING, Suit.HEARTS),
                card(Rank.QUEEN, Suit.CLUBS), card(Rank.JACK, Suit.DIAMONDS), card(Rank.TEN, Suit.SPADES));

        assertThrows(IllegalArgumentException.class, () -> HandEvaluator.evaluate(tooFew));
    }

    @Test
    void testIdenticalHands() {
        List<Card> hand = cards(card(Rank.ACE, Suit.SPADES), card(Rank.ACE, Suit.HEARTS), card(Rank.KING, Suit.CLUBS),
                card(Rank.KING, Suit.DIAMONDS), card(Rank.QUEEN, Suit.SPADES), card(Rank.TWO, Suit.HEARTS),
                card(Rank.THREE, Suit.HEARTS));

        EvaluatedHand best1 = HandEvaluator.evaluate(hand);
        EvaluatedHand best2 = HandEvaluator.evaluate(hand);

        assertTrue(best1.isTie(best2));
        assertEquals(0, best1.compareTo(best2));
    }
}
