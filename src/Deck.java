//Todo: Create and .Deal method
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    /**
     * Created by james on 6/09/2016.
     * ArrayList to store, shuffle and deal Card type objects.
     */

    ArrayList<Card> cards = new ArrayList<>();

    public void addToDeck(Card card){
        cards.add(card);
    }

    public void displayCards(){
        for (int i=0; i<cards.size(); ++i){
            cards.get(i).printAttributes();
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void initialDeal(Game newGame){
//        TODO: THIS!
//        for each player:
//            for i=0<9:
//                cardFromTopofDeck.remove
//                playerhand.add(cardFromTopofDeck)
        for(Player player:newGame.players){
            for(int i = 0; i < 8; i++){
                player.hand.add(cards.get(0));
                cards.remove(0);
            }
        }
    }
}
