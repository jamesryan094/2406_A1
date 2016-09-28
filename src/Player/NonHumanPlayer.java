package Player;

import Cards.Card;
import Deck.Deck;
import java.util.Random;

/**
 * Created by james on 22/09/2016.
 */
public class NonHumanPlayer extends Player {

    public NonHumanPlayer(int id){
        super(id);
    }

    public Card playAnyCard() {
        int cardChoice;
        Random rn = new Random();
        cardChoice = rn.nextInt(getHand().size());
        return getHand().remove(cardChoice);
    }

    @Override
    public boolean hasPlayableCards(Card lastPlayedCard, String currentTrump) {
        boolean haveCard = false;
        for(int i=0;i<getHand().size();++i){
            if (getHand().get(i).canPlayOn(lastPlayedCard, currentTrump)&&!haveCard){
                haveCard=true;
            }
        }
        return haveCard;
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().add(deck.removeCard(0));
    }

    @Override
    public Card playCard(int cardChoiceIndex) {
        return getHand().remove(cardChoiceIndex);
    }

    @Override
    public String getTrumpCategoryChoice() {
        String trumpChoice;
        Random rn = new Random();
        int trumpChoiceNum = rn.nextInt(5);
        System.out.println(trumpChoiceNum);
        switch (trumpChoiceNum){
            case 0:
                trumpChoice = "Cleavage";
                break;
            case 1:
                trumpChoice = "Crustal Abundance";
                break;
            case 2:
                trumpChoice = "Economic Value";
                break;
            case 3:
                trumpChoice = "Hardness";
                break;
            case 4:
                trumpChoice = "Specific Gravity";
                break;
            default:
                System.out.println("Error NonHumanPlayer GetTrumpCategoryChoice()");
                trumpChoice ="Cleavage";
        }
        return trumpChoice;
    }
}
