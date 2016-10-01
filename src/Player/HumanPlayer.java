package Player;

import Cards.Card;
import Deck.Deck;

/**
 * Created by james on 19/09/2016.
 */
public class HumanPlayer extends Player{

    public HumanPlayer(String name){
        super(0, name);
//        this.name = name;

    }

    @Override
    public Card playCard(int cardChoice) {
        return getHand().remove(cardChoice);
    }

    @Override
    public String getTrumpCategoryChoice() {
        return null;
    }

    @Override
    public Card playAnyMineralCard() {
        return null;
    }

    @Override
    public boolean hasPlayableCards(Card lastPlayedCard, String currentTrump) {
        return false;
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().add(deck.removeCard(0));
    }

    @Override
    public Card playAnyCard() {
        return null;
    }


}
