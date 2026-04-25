package cegepst.Test;

import cegepst.Entities.Card;
import cegepst.Entities.Rank;
import cegepst.Entities.Suit;
import cegepst.Entities.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest extends Validator {
    @Test
    void isFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.HEART)); // [A ♥] [Q ♥] [6 ♥] [J ♥] [2 ♥]
        cards.add(new Card(Rank.QUEEN, Suit.HEART));
        cards.add(new Card(Rank.SIX, Suit.HEART));
        cards.add(new Card(Rank.JACK, Suit.HEART));
        cards.add(new Card(Rank.TWO, Suit.HEART));

        assertTrue(Validator.isFlush(cards));
    }

    @Test
    void isStraightFlush() {
        ArrayList<Card> cards = new ArrayList<>(); // [4 ♥] [5 ♥] [6 ♥] [7 ♥] [8 ♥]
        cards.add(new Card(Rank.FOUR, Suit.HEART));
        cards.add(new Card(Rank.FIVE, Suit.HEART));
        cards.add(new Card(Rank.SIX, Suit.HEART));
        cards.add(new Card(Rank.SEVEN, Suit.HEART));
        cards.add(new Card(Rank.EIGHT, Suit.HEART));;

        assertTrue(Validator.isStraightFlush(cards));
    }

    @Test
    void isTwoPairs() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.SEVEN, Suit.DIAMOND)); // [7 ♦] [7 ♠] [Q ♠] [Q ♦]
        cards.add(new Card(Rank.SEVEN, Suit.SPADE));
        cards.add(new Card(Rank.QUEEN, Suit.SPADE));
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));

        assertTrue(Validator.isTwoPairs(cards));
    }

    @Test
    void isOnePair() {
        ArrayList<Card> cards = new ArrayList<>(); // [6 ♥] [6 ♦]
        cards.add(new Card(Rank.SIX, Suit.HEART));
        cards.add(new Card(Rank.SIX, Suit.DIAMOND));

        assertTrue(Validator.isOnePair(cards));
    }

    @Test
    void isThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>(); // [6 ♦] [6 ♠] [6 ♥]
        cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
        cards.add(new Card(Rank.EIGHT, Suit.SPADE));
        cards.add(new Card(Rank.EIGHT, Suit.HEART));

        assertTrue(Validator.isThreeOfAKind(cards));
    }

    @Test
    void isFullHouse() {
        ArrayList<Card> cards = new ArrayList<>(); // [K ♠] [K ♦] [3 ♠] [3 ♥] [3 ♦]
        cards.add(new Card(Rank.KING, Suit.SPADE));
        cards.add(new Card(Rank.KING, Suit.DIAMOND));
        cards.add(new Card(Rank.THREE, Suit.SPADE));
        cards.add(new Card(Rank.THREE, Suit.HEART));
        cards.add(new Card(Rank.THREE, Suit.DIAMOND));

        assertTrue(Validator.isFullHouse(cards));
    }

    @Test
    void isRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<>(); // [10 ♣] [J ♣] [Q ♣] [K ♣] [A ♣]
        cards.add(new Card(Rank.TEN, Suit.CLUB));
        cards.add(new Card(Rank.JACK, Suit.CLUB));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB));
        cards.add(new Card(Rank.KING, Suit.CLUB));
        cards.add(new Card(Rank.ACE, Suit.CLUB));

        assertTrue(Validator.isRoyalFlush(cards));
    }

    @Test
    void isFourOfAKind() {
        ArrayList<Card> cards = new ArrayList<>(); // [Q ♦] [Q ♣] [Q ♥] [Q ♠]
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB));
        cards.add(new Card(Rank.QUEEN, Suit.HEART));
        cards.add(new Card(Rank.QUEEN, Suit.SPADE));

        assertTrue(Validator.isFourOfAKind(cards));
    }

    @Test
    void isStraight() {
        ArrayList<Card> cards = new ArrayList<>(); // [K ♠] [Q ♦] [J ♣] [10 ♥] [9 ♠]
        cards.add(new Card(Rank.KING, Suit.SPADE));
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));
        cards.add(new Card(Rank.JACK, Suit.CLUB));
        cards.add(new Card(Rank.TEN, Suit.HEART));
        cards.add(new Card(Rank.NINE, Suit.SPADE));

        assertTrue(Validator.isStraight(cards));
    }
}