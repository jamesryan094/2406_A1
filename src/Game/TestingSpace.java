package Game;//import Cards.Card;
//import Cards.MineralCard;
//import Deck.Deck;
//import Deck.GenerateDeckFromPLIST;
import Cards.*;
import Deck.*;
import Game.*;
import Player.*;
import Trumps.*;

/**
 * Created by james on 14/09/2016.
 */
public class TestingSpace {
    public static void main(String[] args) {

        Game newGame = RunGame.prepareNewGame();
        System.out.println("Current Player: " + newGame.getCurrentPlayer().getName());
//        System.out.print(MENU_MESSAGE);
//        String menuChoice = getValidMenuChoice();
//        while (!menuChoice.equals("Q")) {
//            if (menuChoice.equals("P")) {
//                Game newGame = prepareNewGame();
//                System.out.println("Current Player: " + newGame.getCurrentPlayer().getName());
////                if (user is up){
////                    get user card choice
////                            playturn (card choice)
////                }
////                else {
////                    playturn();
////                }
//
//            }
//            System.out.print(MENU_MESSAGE);
//            menuChoice = getValidMenuChoice();
//        }
//        System.out.println("Thank you for playing :V");





//        Deck deck = GenerateDeckFromPLIST.buildDeck();
//        for (Card card : deck.getCards()){
//            if (card instanceof MineralCard) {
//                MineralCard tempCard = (MineralCard) card;
//                System.out.println("String: " + tempCard.getCleavage().toString() + "\nValue: " + tempCard.getCleavage().getValue() + "\n");
//            }
//        }
    }
}
