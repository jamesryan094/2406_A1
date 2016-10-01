package Cards;

import Trumps.TrumpCategory;

import java.util.ArrayList;

public abstract class Card {
    /**
     * Created by James on 31/08/2016.
     * Builds Cards.Card superclass based on information parsed from PLIST file.
     * No Cards.Card type objects will be directly instantiated, instead each game card
     * will belong more specifically to one of three subclasses.
     */

    private String fileName, imageName, cardType, title;

    public Card(ArrayList cardAttributes) {
        fileName = (String) cardAttributes.get(0);
        imageName = (String) cardAttributes.get(1);
        cardType = (String) cardAttributes.get(2);
        title = (String) cardAttributes.get(3);
    }

//    public void printAttributes(){
//        System.out.println("File Name: " +fileName +
//                "\nImage Name: " + imageName +
//                "\nCard Type: " + cardType +
//                "\nTitle: " + title +
//                "\n--------------------");
//    }
    public void printAttributes(){
        System.out.println("\nTitle: " + title +
                "\n--------------------");
    }

    public String getCardType() {
        return cardType;
    }

    public String getTitle() {
        return title;
    }

//    public abstract String

    public abstract String getCurrentTrumpValueAsString(String currentTrumpCategory);

    public abstract boolean canPlayOn(Card otherCard, String currentTrumpCategory);

    public boolean isGeologist() {
        return title.equals("The Geologist");
    }
}
