import Cards.Card;
import Cards.MineralCard;
import Deck.Deck;
import Deck.GenerateDeckFromPLIST;

/**
 * Created by james on 14/09/2016.
 */
public class TestingSpace {
    public static void main(String[] args) {
        Deck deck = GenerateDeckFromPLIST.buildDeck();
        for (Card card : deck.getCards()){
            if (card instanceof MineralCard) {
                MineralCard tempCard = (MineralCard) card;
                System.out.println("String: " + tempCard.getCleavage().toString() + "\nValue: " + tempCard.getCleavage().getValue() + "\n");
            }
        }
    }
}
