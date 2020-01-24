package cegepst.ChainOfResponsibiliy;
import cegepst.Entities.Card;
import java.util.ArrayList;

public class Pair extends CoR {

    private CoR next;

    @Override
    public void setNext(CoR next) { this.next = next; }

    @Override
    public boolean check(ArrayList<Card> hole, ArrayList<Card> community) {
        if(isPair(hole, community)) {
            System.out.println("C'est une paire!");
            return true;
        }

        if(next != null){
            System.out.println("Ce n'est pas une paire.");
            return next.check(hole, community);
        }
        return false;
    }

    private boolean isPair(ArrayList<Card> hole, ArrayList<Card> community) {
        return false;
    }
}