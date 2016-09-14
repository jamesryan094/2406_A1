import java.util.ArrayList;

//Todo: This may be better as interface or abstract class.
public class Card {
    /**
     * Created by James on 31/08/2016.
     * Builds Card superclass based on information parsed from PLIST file.
     * No Card type objects will be directly instantiated, instead each game card
     * will belong more specifically to one of three subclasses.
     */

    String fileName, imageName, cardType, title;

    public Card(ArrayList cardAttributes) {
        fileName = (String) cardAttributes.get(0);
        imageName = (String) cardAttributes.get(1);
        cardType = (String) cardAttributes.get(2);
        title = (String) cardAttributes.get(3);
    }

    public void printAttributes(){
        System.out.println("File Name: " +fileName +
                "\nImage Name: " + imageName +
                "\nCard Type: " + cardType +
                "\nTitle: " + title +
                "\n--------------------");
    }
}
