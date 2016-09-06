import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 */
public class Deck {

    ArrayList<Card> cards = new ArrayList<Card>();

    public void addToDeck(Card card){
        cards.add(card);
    }

    public void displayCards(){
        for (int i=0; i<cards.size(); ++i){
            System.out.println(cards.get(i).title);
        }
    }
}
