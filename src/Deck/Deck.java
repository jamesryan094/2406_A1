package Deck;

import java.util.ArrayList;
import java.util.Collections;
import Cards.Card;

public class Deck {

    /**
     * Created by james on 6/09/2016.
     * ArrayList to store, shuffle and deal Cards.Card type objects.
     */

    private ArrayList<Card> cards = new ArrayList<>();
    private boolean hasCards = true;

    void addToDeck(Card card){
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

//    public void initialDeal(Game.Game newGame){
//        for(Player.Player player:newGame.players){
//            for(int i = 0; i < 8; i++){
//                player.hand.add(cards.get(0));
//                cards.remove(0);
//            }
//        }
//    }

    public  ArrayList<Card> dealHand(int numCardsPerHand){
        ArrayList<Card> hand = new ArrayList<>();
        for(int i = 0; i < numCardsPerHand; i++){
                hand.add(this.cards.remove(i));
            }
        return hand;
    }

    private Card getCard(int i) {
        return cards.get(i);
    }

    public void removeRuleCards() {
//        for (Cards.Card card : cards){
//            String currentCardType = card.getCardType();
//            if (currentCardType.equals("rule")){
//                cards.remove(card);
//            }
//        }

//        Todo: iterate
        cards.remove(cards.size()-1);
        cards.remove(cards.size()-1);
        cards.remove(cards.size()-1);

    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
