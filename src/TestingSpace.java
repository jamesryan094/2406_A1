/**
 * Created by james on 14/09/2016.
 */
public class TestingSpace {
    public static void main(String[] args) {
        Deck deck = GenerateDeckFromPLIST.buildDeck();
        for (Card card:deck.cards){
            if (card instanceof MineralCard) {
                MineralCard tempCard = (MineralCard) card;
                System.out.println("String: " + tempCard.specificGravity.toString() + "\nDouble: " + tempCard.specificGravity.getValue() + "\n");
            }
        }
    }
}
