package cegepst.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuitTest {

    @Test
    void heartsEmoji() {
        assertEquals("♥️", Suit.HEARTS.toEmoji());
    }

    @Test
    void diamondsEmoji() {
        assertEquals("♦️", Suit.DIAMONDS.toEmoji());
    }

    @Test
    void clubsEmoji() {
        assertEquals("♣️", Suit.CLUBS.toEmoji());
    }

    @Test
    void spadesEmoji() {
        assertEquals("♠️", Suit.SPADES.toEmoji());
    }

    @Test
    void fourSuitsExist() {
        assertEquals(4, Suit.values().length);
    }
}
