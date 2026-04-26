package cegepst.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    void dealGivesEachPlayerTwoHoleCards() {
        Dealer dealer = new Dealer();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        dealer.deal(List.of(p1, p2));

        assertEquals(2, p1.getHole().size());
        assertEquals(2, p2.getHole().size());
    }

    @Test
    void dealProducesFiveCommunityCards() {
        Dealer dealer = new Dealer();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        dealer.deal(List.of(p1, p2));

        assertEquals(5, dealer.getCommunity().size());
    }

    @Test
    void dealResetsPlayerHandsBeforeDealing() {
        Dealer dealer = new Dealer();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        dealer.deal(List.of(p1, p2));
        dealer.deal(List.of(p1, p2));

        assertEquals(2, p1.getHole().size());
    }

    @Test
    void dealDealsDistinctCards() {
        Dealer dealer = new Dealer();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        dealer.deal(List.of(p1, p2));

        assertNotEquals(p1.getHole().get(0).toString(), p2.getHole().get(0).toString());
    }

    @Test
    void communityCardsAreNotInPlayersHoles() {
        Dealer dealer = new Dealer();
        Player p1 = new Player("Alice");

        dealer.deal(List.of(p1));

        var communityStrings = dealer.getCommunity().stream().map(Object::toString).toList();
        var holeStrings = p1.getHole().stream().map(Object::toString).toList();

        for (String holeCard : holeStrings) {
            assertFalse(communityStrings.contains(holeCard));
        }
    }

    @Test
    void flopSizeIs3() {
        assertEquals(3, new Dealer().getFlopSize());
    }

    @Test
    void turnSizeIs4() {
        assertEquals(4, new Dealer().getTurnSize());
    }

    @Test
    void riverSizeIs5() {
        assertEquals(5, new Dealer().getRiverSize());
    }

    @Test
    void resetClearsCommunityCards() {
        Dealer dealer = new Dealer();
        Player p1 = new Player("Alice");
        dealer.deal(List.of(p1));
        dealer.reset();
        assertEquals(0, dealer.getCommunity().size());
    }
}
