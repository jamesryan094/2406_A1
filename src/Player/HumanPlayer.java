package Player;

import Cards.Card;

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
}
