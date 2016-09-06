import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 */


public class TrumpCard extends Card {
    String subTitle;
    public TrumpCard(ArrayList cardAttributes){
        super(cardAttributes);
        subTitle = cardAttributes.get(4).toString();
    }

    public void displayAttributes(){
        super.displayAttributes();
        System.out.println(subTitle);
    }

}
