package cegepst.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTypeTest {

    @Test
    void royalFlushRankIs10() {
        assertEquals(10, HandType.ROYAL_FLUSH.getRank());
    }

    @Test
    void straightFlushRankIs9() {
        assertEquals(9, HandType.STRAIGHT_FLUSH.getRank());
    }

    @Test
    void fourOfAKindRankIs8() {
        assertEquals(8, HandType.FOUR_OF_A_KIND.getRank());
    }

    @Test
    void fullHouseRankIs7() {
        assertEquals(7, HandType.FULL_HOUSE.getRank());
    }

    @Test
    void flushRankIs6() {
        assertEquals(6, HandType.FLUSH.getRank());
    }

    @Test
    void straightRankIs5() {
        assertEquals(5, HandType.STRAIGHT.getRank());
    }

    @Test
    void threeOfAKindRankIs4() {
        assertEquals(4, HandType.THREE_OF_A_KIND.getRank());
    }

    @Test
    void twoPairRankIs3() {
        assertEquals(3, HandType.TWO_PAIR.getRank());
    }

    @Test
    void onePairRankIs2() {
        assertEquals(2, HandType.ONE_PAIR.getRank());
    }

    @Test
    void highCardRankIs1() {
        assertEquals(1, HandType.HIGH_CARD.getRank());
    }

    @Test
    void allRanksAreUnique() {
        long distinct = java.util.Arrays.stream(HandType.values()).mapToInt(HandType::getRank).distinct().count();
        assertEquals(10, distinct);
    }

    @Test
    void royalFlushDisplayName() {
        assertEquals("Quinte Royale", HandType.ROYAL_FLUSH.getDisplayName());
    }

    @Test
    void highCardDisplayName() {
        assertEquals("Hauteur", HandType.HIGH_CARD.getDisplayName());
    }

    @Test
    void fullHouseDisplayName() {
        assertEquals("Full", HandType.FULL_HOUSE.getDisplayName());
    }
}
