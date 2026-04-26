package cegepst.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getRankReturnsNumericValue() {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        assertEquals(14, card.getRank());
    }

    @Test
    void getRankForTwoReturns2() {
        Card card = new Card(Rank.TWO, Suit.HEARTS);
        assertEquals(2, card.getRank());
    }

    @Test
    void toStringContainsSymbolAndEmoji() {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        String str = card.toString();
        assertTrue(str.contains("A"));
        assertTrue(str.contains("♠️"));
    }

    @Test
    void toStringFormat() {
        Card card = new Card(Rank.KING, Suit.HEARTS);
        assertEquals("[K ♥️]", card.toString());
    }

    @Test
    void rankFieldIsAccessible() {
        Card card = new Card(Rank.QUEEN, Suit.CLUBS);
        assertEquals(Rank.QUEEN, card.rank);
    }

    @Test
    void suitFieldIsAccessible() {
        Card card = new Card(Rank.JACK, Suit.DIAMONDS);
        assertEquals(Suit.DIAMONDS, card.suit);
    }
}
