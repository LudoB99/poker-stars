package cegepst.Entities;

import cegepst.ChainOfResponsibiliy.CoR;
import cegepst.ChainOfResponsibiliy.RoyalFlush;
import cegepst.ChainOfResponsibiliy.StraightFlush;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player player = new Player("vous");
    private Player opponent = new Player("votre adversaire");
    private Scanner input;
    private Checker check;

    public Game() {
        input = new Scanner(System.in);
        check = new Checker();
    }

    public void start() {
        while (true) {
            Dealer dealer = new Dealer();
            dealer.startRound(player, opponent);
            Messenger.showPlayerHole(player);
            Messenger.showOpponentHole(opponent);
            if(isFolding()) {break;}
            dealer.showTurn();
            if(isFolding()) {break;}
            dealer.showRiver();
            dealer.endTurn(getWinner(player, opponent, dealer.getCommunity()));
            if(isEnding()) {break;}
        }
        Messenger.showEndMessage();
    }

    private Player getWinner(Player player, Player opponent, ArrayList<Card> community) {
        player.setHole(getBoard(player.getHole(), community));
        opponent.setHole(getBoard(opponent.getHole(), community));
        check.process(player);
        check.process(opponent);
        Messenger.showPlayerHand(player);
        Messenger.showOpponendHand(opponent);
        if(player.getHand().getWeight() == opponent.getHand().getWeight()) {
            return null;
        }

        if(player.getHand().getWeight() >= opponent.getHand().getWeight()) {
            return player;
        }
        return opponent;
    }


    private boolean isEnding() {
        Messenger.askIfContinue();
        return input.next().equals("n");
    }

    private boolean isFolding() {
        Messenger.askIfFolding();
        return input.nextInt() == 1;
    }

    private ArrayList<Card> getBoard (ArrayList<Card> hole, ArrayList<Card> community) {
        ArrayList<Card> result = new ArrayList<Card>();
        result.addAll(hole);
        result.addAll(community);
        return result;
    }
}
