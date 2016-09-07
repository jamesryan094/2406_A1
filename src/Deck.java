//Todo: Create .Shuffle and .Deal methods

import java.util.ArrayList;

public class Deck {

    /**
     * Created by james on 6/09/2016.
     * ArrayList to store, shuffle and deal Card type objects.
     */

    ArrayList<Card> cards = new ArrayList<Card>();

    public void addToDeck(Card card){
        cards.add(card);
    }

    public void displayCards(){
        for (int i=0; i<cards.size(); ++i){
            cards.get(i).printAttributes();
        }
    }
}
