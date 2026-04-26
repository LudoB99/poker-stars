package cegepst.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    void aceIsNumeric14() {
        assertEquals(14, Rank.ACE.toNumeric());
    }

    @Test
    void kingIsNumeric13() {
        assertEquals(13, Rank.KING.toNumeric());
    }

    @Test
    void queenIsNumeric12() {
        assertEquals(12, Rank.QUEEN.toNumeric());
    }

    @Test
    void jackIsNumeric11() {
        assertEquals(11, Rank.JACK.toNumeric());
    }

    @Test
    void tenIsNumeric10() {
        assertEquals(10, Rank.TEN.toNumeric());
    }

    @Test
    void twoIsNumeric2() {
        assertEquals(2, Rank.TWO.toNumeric());
    }

    @Test
    void allRanksHaveUniqueNumericValues() {
        long distinct = java.util.Arrays.stream(Rank.values()).mapToInt(Rank::toNumeric).distinct().count();
        assertEquals(13, distinct);
    }

    @Test
    void aceSymbol() {
        assertEquals("A", Rank.ACE.toSymbol());
    }

    @Test
    void kingSymbol() {
        assertEquals("K", Rank.KING.toSymbol());
    }

    @Test
    void queenSymbol() {
        assertEquals("Q", Rank.QUEEN.toSymbol());
    }

    @Test
    void jackSymbol() {
        assertEquals("J", Rank.JACK.toSymbol());
    }

    @Test
    void tenSymbol() {
        assertEquals("10", Rank.TEN.toSymbol());
    }

    @Test
    void twoSymbol() {
        assertEquals("2", Rank.TWO.toSymbol());
    }
}
