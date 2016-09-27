package Player;

import Cards.Card;

/**
 * Created by james on 22/09/2016.
 */
public class NonHumanPlayer extends Player {

    public NonHumanPlayer(int id){
        super(id);
    }

    @Override
    public Card playCard(int cardChoice) {
        return null;
    }
}
