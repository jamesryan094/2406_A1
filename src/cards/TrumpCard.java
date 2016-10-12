package cards;

import java.util.ArrayList;

/**
 * One of two playable card types in the game. Assigns a trump category to a trump card. Is used in
 * Mineral Supertrumps game to change the current trump category in play or reset teh current trump
 * value. Created by james on 6/09/2016.
 */
public class TrumpCard extends Card {

  private String subTitle;

  /**
   * Calls super Card constructor, then assigns addition field to represent the trump category that
   * is set upon playing this card.
   *
   * @param cardAttributes String cast ArrayList of card information.
   */
  public TrumpCard(ArrayList cardAttributes) {
    super(cardAttributes);
    subTitle = cardAttributes.get(4).toString();
  }

  public void printAttributes() {
    super.printAttributes();
    System.out.println(subTitle);
  }

  public String getCurrentTrumpValueAsString(String currentTrumpCategory) {
    return this.subTitle;
  }

  /**
   * As a Supertrump card can be played on any card, this will always return true.
   *
   * @param otherCard The current card in play
   * @param currentTrumpCategory The current trump category in play.
   * @return true
   */
  public boolean canPlayOn(Card otherCard, String currentTrumpCategory) {
    return true;
  }
}
