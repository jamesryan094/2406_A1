import java.util.ArrayList;

/**
 * Created by James on 31/08/2016.
 * Builds card objects based on information parsed from PLIST file
 */
public class Card {
    String fileName, imageName, cardType,
    title;



    public Card(ArrayList cardAttributes) {
        fileName = (String) cardAttributes.get(0);
        imageName = (String) cardAttributes.get(1);
        cardType = (String) cardAttributes.get(2);
        title = (String) cardAttributes.get(3);
    }


//        Pull array contents, may need to convert occurrenceArray ArrayList to String[]
//        for (int x; x<cardAttributes.get(7){
//
//        }


    public void displayAttributes(){
        System.out.println(fileName + imageName + cardType + title);
    }
}
