package Player;

import Cards.Card;
import Deck.Deck;

/**
 * Human players are little restriction on what they can try in a game however, have the same
 * restrictions for what is considered valid in a game as a nonhuman player. Created by james on
 * 19/09/2016.
 */
public class HumanPlayer extends Player {

  public HumanPlayer(String name) {
    super(0, name);
  }

  @Override
  public Card playCard(int cardChoice) {
    return getHand().remove(cardChoice);
  }

  @Override
  public String getTrumpCategoryChoice() {
    return null;
  }

  @Override
  public Card playAnyMineralCard() {
    return null;
  }

  @Override
  public boolean hasPlayableCards(Card lastPlayedCard, String currentTrump) {
    return false;
  }

  @Override
  public void drawCard(Deck deck) {
    getHand().add(deck.removeCard(0));
  }

  @Override
  public Card playAnyCard() {
    return null;
  }
}
