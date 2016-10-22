package player;

import cards.Card;
import deck.Deck;

import java.util.ArrayList;

/**
 * Player objects represent a person playing Mineral Supertrumps. Abstract superclass to human and
 * nonHuman player, outlining their methods and permissions, before being specified further in
 * appropriate subclasses. The distinction between human and nonhuman player was pertenant to the
 * game's functioning. While many methods seem to overlap, others are so vastly different that the
 * abstract player superclass became necessary. Created by james on 6/09/2016.
 */
public abstract class Player {

  private int id;
  private ArrayList<Card> hand;
  private String name;
  private boolean isHuman;
  private boolean hasPassed;

  /**
   * Super constructor for nonHuman players. Assigns a name based on id attribute
   *
   * @param id a number used to differentiate between players and correspond to player's index in
   *     Game attribute 'players'
   */
  Player(int id) {
    isHuman = false;
    this.id = id;
    String[] NAMES = {"Matt", "Mark", "Luke", "John"};
    name = NAMES[id - 1];
    hand = new ArrayList<>();
    hasPassed = false;
  }

  /**
   * Super constructor for Human players. Assigns a name based on passed in Username argument.
   *
   * @param id the human player's ID (will be 0).
   * @param userName retrieved from the user in runGame, used to reference player throughout game.
   */
  Player(int id, String userName) {
    isHuman = true;
    this.id = id;
    name = userName;
    hand = new ArrayList<>();
    hasPassed = false;
  }

  //Combo Functionality

  /**
   * Removes the "The Geophysicist" and the "Magnetite" card from the current player's hand on one
   * turn.
   *
   * @return Magnetite card
   */
  public Card playCombo() {
    Card mag = null;
    Card geoPhys = null;

    for (Card aHand : hand) {
      if (aHand.getTitle().equals("The Geophysicist")) {
        geoPhys = aHand;
      }
      if (aHand.getTitle().equals("Magnetite")) {
        mag = aHand;
      }
    }
    hand.remove(geoPhys);
    hand.remove(mag);
    return mag;
  }

  /**
   * Determines weather the current player's hand currently contains both the "The Geophysicist" and
   * the "Magnetite".
   *
   * @return true if current player's hand contains the combo.
   */
  public boolean hasCombo() {
    int numComboCards = 0;
    for (Card card : this.hand) {
      if (card.getTitle().equals("The Geophysicist") || card.getTitle().equals("Magnetite")) {
        ++numComboCards;
      }
    }
    return numComboCards == 2;
  }

  //Abstract Declarations
  public abstract Card playCard(int cardChoice);
  public abstract void playCard(Card cardChoice);


  public abstract Card playAnyCard();

  public abstract String getTrumpCategoryChoice();

  public abstract Card playAnyMineralCard();

  public abstract boolean hasPlayableCards(Card lastPlayedCard, String currentTrump);

  public abstract void drawCard(Deck deck);

  //Getters/Setters
  public void setCurrentHand(ArrayList<Card> currentHand) {
    this.hand = currentHand;
  }

  public void setHasPassed(boolean hasPassed) {
    this.hasPassed = hasPassed;
  }

  public int getId() {
    return id;
  }

  public boolean getHasPassed() {
    return hasPassed;
  }

  public ArrayList<Card> getHand() {
    return hand;
  }

  public String getName() {
    return name;
  }

  public boolean isHuman() {
    return this.isHuman;
  }
}
