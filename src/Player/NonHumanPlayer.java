package Player;

import Cards.Card;

import java.util.Random;

/**
 * Created by james on 22/09/2016.
 */
public class NonHumanPlayer extends Player {

    public NonHumanPlayer(int id){
        super(id);
    }

    @Override
    public Card playCard(int cardChoice) {
//        System.out.println(getHand().size());
        Random rn = new Random();
        cardChoice = rn.nextInt(getHand().size());
        return getHand().remove(cardChoice);
    }

    @Override
    public String getTrumpCategoryChoice() {
        String trumpChoice;
        Random rn = new Random();
        int trumpChoiceNum = rn.nextInt(6);
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
