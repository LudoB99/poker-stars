package cegepst.validation;

import cegepst.domain.Card;
import cegepst.domain.HandType;
import cegepst.domain.Rank;
import cegepst.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatedHandTest {

    private Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    private List<Card> royalFlushCards() {
        return List.of(card(Rank.ACE, Suit.SPADES), card(Rank.KING, Suit.SPADES), card(Rank.QUEEN, Suit.SPADES),
                card(Rank.JACK, Suit.SPADES), card(Rank.TEN, Suit.SPADES));
    }

    private List<Card> highCardCards() {
        return List.of(card(Rank.ACE, Suit.SPADES), card(Rank.KING, Suit.HEARTS), card(Rank.QUEEN, Suit.CLUBS),
                card(Rank.JACK, Suit.DIAMONDS), card(Rank.NINE, Suit.SPADES));
    }

    @Test
    void compareToSameHandReturns0() {
        EvaluatedHand hand = new EvaluatedHand(HandType.FLUSH, highCardCards(), new int[] { 14, 13, 12, 11, 9 });
        assertEquals(0, hand.compareTo(hand));
    }

    @Test
    void isTieWithSameKickers() {
        EvaluatedHand h1 = new EvaluatedHand(HandType.ONE_PAIR, highCardCards(), new int[] { 7, 13, 12, 11 });
        EvaluatedHand h2 = new EvaluatedHand(HandType.ONE_PAIR, highCardCards(), new int[] { 7, 13, 12, 11 });
        assertTrue(h1.isTie(h2));
    }

    @Test
    void notTieWithDifferentHandTypes() {
        EvaluatedHand royal = new EvaluatedHand(HandType.ROYAL_FLUSH, royalFlushCards(), new int[] { 14 });
        EvaluatedHand high = new EvaluatedHand(HandType.HIGH_CARD, highCardCards(), new int[] { 14, 13, 12, 11, 9 });
        assertFalse(royal.isTie(high));
    }

    @Test
    void notTieWithDifferentKickers() {
        EvaluatedHand h1 = new EvaluatedHand(HandType.ONE_PAIR, highCardCards(), new int[] { 7, 13, 12, 11 });
        EvaluatedHand h2 = new EvaluatedHand(HandType.ONE_PAIR, highCardCards(), new int[] { 7, 13, 12, 10 });
        assertFalse(h1.isTie(h2));
    }

    @Test
    void compareToSameTypeHigherKickerWins() {
        EvaluatedHand aceHigh = new EvaluatedHand(HandType.HIGH_CARD, highCardCards(), new int[] { 14, 13, 12, 11, 9 });
        EvaluatedHand kingHigh = new EvaluatedHand(HandType.HIGH_CARD, highCardCards(),
                new int[] { 13, 12, 11, 10, 9 });
        assertNotEquals(0, aceHigh.compareTo(kingHigh));
    }

    @Test
    void compareToReturnZeroForIdenticalKickers() {
        EvaluatedHand h1 = new EvaluatedHand(HandType.TWO_PAIR, highCardCards(), new int[] { 14, 13, 12 });
        EvaluatedHand h2 = new EvaluatedHand(HandType.TWO_PAIR, highCardCards(), new int[] { 14, 13, 12 });
        assertEquals(0, h1.compareTo(h2));
        assertTrue(h1.isTie(h2));
    }

    @Test
    void toStringContainsHandTypeName() {
        EvaluatedHand hand = new EvaluatedHand(HandType.FLUSH, highCardCards(), new int[] { 14, 13, 12, 11, 9 });
        assertTrue(hand.toString().contains("Flush"));
    }

    @Test
    void recordAccessors() {
        List<Card> cards = royalFlushCards();
        int[] kickers = { 14 };
        EvaluatedHand hand = new EvaluatedHand(HandType.ROYAL_FLUSH, cards, kickers);

        assertEquals(HandType.ROYAL_FLUSH, hand.type());
        assertEquals(cards, hand.cards());
        assertArrayEquals(kickers, hand.kickers());
    }
}
