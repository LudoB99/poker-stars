package cegepst.Entities;

import java.util.Arrays;
import java.util.List;

public class Sorter {

    public static List<Card> sortByRank(List<Card> cards) {
        Card[] cardsArray = cards.toArray(new Card[cards.size()]);
        int min_j;
        for (int i = 0 ; i < cardsArray.length ; i ++ )
        {
            min_j = i;

            for (int j = i+1 ; j < cardsArray.length ; j++ )
            {
                if (cardsArray[j].getRank().getValue() < cardsArray[min_j].getRank().getValue() )
                {
                    min_j = j;
                }
            }
            Card help = cardsArray[i];
            cardsArray[i] = cardsArray[min_j];
            cardsArray[min_j] = help;
        }
        return Arrays.asList(cardsArray);
    }

    public static List<Card> sortBySuit(List<Card> cards) {
        Card[] cardsArray = cards.toArray(new Card[cards.size()]);
        int min_j;
        for (int i = 0 ; i < cardsArray.length ; i ++ ) {
            min_j = i;

            for (int j = i+1 ; j < cardsArray.length ; j++ ) {
                if ( cardsArray[j].getSuit().getValue() < cardsArray[min_j].getSuit().getValue() ) {
                    min_j = j;
                }
            }
            Card help = cardsArray[i];
            cardsArray[i] = cardsArray[min_j];
            cardsArray[min_j] = help;
        }
        return Arrays.asList(cardsArray);
    }
}
