package Cards;

import java.util.ArrayList;

public class TrumpCard extends Card {
    /**
     * Created by james on 6/09/2016.
     *
     */

    private String subTitle;

    public TrumpCard(ArrayList cardAttributes){
        super(cardAttributes);
        subTitle = cardAttributes.get(4).toString();
    }

    public void printAttributes(){
        super.printAttributes();
        System.out.println(subTitle);
    }

    public String getCurrentTrumpValueAsString(String currentTrumpCategory){
        return this.subTitle;
    }

}
