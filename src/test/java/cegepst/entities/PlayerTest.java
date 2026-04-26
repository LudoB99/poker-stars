package cegepst.entities;

import cegepst.domain.Card;
import cegepst.domain.Rank;
import cegepst.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void newPlayerHasEmptyHole() {
        Player player = new Player("Alice");
        assertTrue(player.getHole().isEmpty());
    }

    @Test
    void newPlayerHasName() {
        Player player = new Player("Bob");
        assertEquals("Bob", player.name);
    }

    @Test
    void receiveCardAddsToHole() {
        Player player = new Player("Alice");
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        assertEquals(1, player.getHole().size());
    }

    @Test
    void receivesTwoCards() {
        Player player = new Player("Alice");
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.KING, Suit.HEARTS));
        assertEquals(2, player.getHole().size());
    }

    @Test
    void resetClearsHole() {
        Player player = new Player("Alice");
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.KING, Suit.HEARTS));
        player.reset();
        assertTrue(player.getHole().isEmpty());
    }

    @Test
    void setHoleReplacesCards() {
        Player player = new Player("Alice");
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));

        ArrayList<Card> newHole = new ArrayList<>();
        newHole.add(new Card(Rank.TWO, Suit.CLUBS));
        player.setHole(newHole);

        assertEquals(1, player.getHole().size());
        assertEquals(Rank.TWO, player.getHole().get(0).rank);
    }

    @Test
    void holeCardsAreCorrectCards() {
        Player player = new Player("Alice");
        Card ace = new Card(Rank.ACE, Suit.SPADES);
        player.receiveCard(ace);
        assertSame(ace, player.getHole().get(0));
    }
}
