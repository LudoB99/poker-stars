package cegepst.entities;

import cegepst.domain.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void newDeckHas52Cards() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    void drawReducesSizeByOne() {
        Deck deck = new Deck();
        deck.draw(0);
        assertEquals(51, deck.size());
    }

    @Test
    void drawReturnsCard() {
        Deck deck = new Deck();
        Card drawn = deck.draw(0);
        assertNotNull(drawn);
    }

    @Test
    void drawOutOfBoundsReturnsNull() {
        Deck deck = new Deck();
        Card result = deck.draw(52);
        assertNull(result);
    }

    @Test
    void drawLastCard() {
        Deck deck = new Deck();
        Card card = deck.draw(51);
        assertNotNull(card);
        assertEquals(51, deck.size());
    }

    @Test
    void resetRestores52Cards() {
        Deck deck = new Deck();
        deck.draw(0);
        deck.draw(0);
        deck.draw(0);
        deck.reset();
        assertEquals(52, deck.size());
    }

    @Test
    void shufflePreservesSize() {
        Deck deck = new Deck();
        deck.shuffle();
        assertEquals(52, deck.size());
    }

    @Test
    void drawAllCards() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.draw(0));
        }
        assertEquals(0, deck.size());
    }
}
