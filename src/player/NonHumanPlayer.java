package Player;

import Cards.Card;
import Deck.Deck;
import java.util.Random;

/**
 * Provides functionality for nonHuman players playing a turn in Mineral Supertrumps. Created by
 * james on 19/09/2016.
 */
public class NonHumanPlayer extends Player {

  public NonHumanPlayer(int id) {
    super(id);
  }

  /**
   * Used when starting a round. As per the game's rules, any card can be played while beginning a
   * round (bar the very first turn). As such, a random card is picked.
   *
   * @return a Card, selected randomly.
   */
  public Card playAnyCard() {
    int cardChoice;
    Random rn = new Random();
    cardChoice = rn.nextInt(getHand().size());
    return getHand().remove(cardChoice);
  }

  /**
   * Goes through player's hand and checks each card's type. Returns the first card of mineral type.
   *
   * @return A Mineral Card.
   */
  public Card playAnyMineralCard() {
    for (int i = 0; i < getHand().size(); ++i) {
      if (getHand().get(i).getCardType().equals("play")) {
        return getHand().remove(i);
      }
    }
    return null;
  }

  /**
   * Compares the last played card in the game to each card in the nonHuman player's hand to
   * ultimately determine weather the current player must pass or not.
   *
   * @param lastPlayedCard the card currently in play.
   * @param currentTrump the current trump value.
   * @return true if the current player has any cards that can be played.
   */
  @Override
  public boolean hasPlayableCards(Card lastPlayedCard, String currentTrump) {
    boolean haveCard = false;
    for (int i = 0; i < getHand().size(); ++i) {
      if (getHand().get(i).canPlayOn(lastPlayedCard, currentTrump) && !haveCard) {
        haveCard = true;
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

  /**
   * Generates a random number between 0 and the number of trump categories (5). return a trump
   * choice as a string, corresponding the the random integer.
   *
   * @return a string to be used to set the trump category in play.
   */
  @Override
  public String getTrumpCategoryChoice() {
    String trumpChoice;
    Random rn = new Random();
    int trumpChoiceNum = rn.nextInt(5);
    switch (trumpChoiceNum) {
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
        trumpChoice = "Cleavage";
    }
    return trumpChoice;
  }
}
