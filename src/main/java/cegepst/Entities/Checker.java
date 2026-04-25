package cegepst.Entities;

import cegepst.ChainOfResponsibiliy.*;

import java.util.ArrayList;

public class Checker {
    CoR c1;
    CoR c2;
    CoR c3;
    CoR c4;
    CoR c5;
    CoR c6;
    CoR c7;
    CoR c8;
    CoR c9;
    CoR c10;

    public Checker() {
        c10 = new HighCard(null);
        c9 = new Pair(c10);
        c8 = new TwoPair(c9);
        c7 = new ThreeOfAKind(c8);
        c6 = new Straight(c7);
        c5 = new Flush(c6);
        c4 = new FullHouse(c5);
        c3 = new FourOfAKind(c4);
        c2 = new StraightFlush(c3);
        c1 = new RoyalFlush(c2);
    }

    public void process(Player player) {
        c1.check(player);
    }
}
