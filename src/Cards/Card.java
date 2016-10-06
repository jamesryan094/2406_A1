package Cards;

import java.util.ArrayList;

/**
 * Builds Cards.Card superclass based on information parsed from PLIST file. No Cards.Card type
 * objects will be directly instantiated, instead each game card will belong more specifically to
 * one of two subclasses. Created by James on 31/08/2016.
 */
public abstract class Card {
  private String fileName, imageName, cardType, title;

  /**
   * Assigns attributes that are know to be on all cards, to each card in the deck.
   *
   * @param cardAttributes ArrayList containing information known to be on all cards.
   */
  public Card(ArrayList cardAttributes) {
    fileName = (String) cardAttributes.get(0);
    imageName = (String) cardAttributes.get(1);
    cardType = (String) cardAttributes.get(2);
    title = (String) cardAttributes.get(3);
  }

  public void printAttributes() {
    System.out.println("\nTitle: " + title + "\n--------------------");
  }

  public String getCardType() {
    return cardType;
  }

  public String getTitle() {
    return title;
  }

  public abstract String getCurrentTrumpValueAsString(String currentTrumpCategory);

  public abstract boolean canPlayOn(Card otherCard, String currentTrumpCategory);

  public boolean isGeologist() {
    return title.equals("The Geologist");
  }
}
