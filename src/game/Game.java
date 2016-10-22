package game;

import cards.Card;

import deck.Deck;
import deck.GenerateDeckFromPLIST;

import player.Player;
import player.HumanPlayer;
import player.NonHumanPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Instantiates Game object that represents the state of any Mineral Supertrumps Game. Attributes
 * are manipulated through method calls from an adjacent "controller" file; RunGame. Class also
 * stores large string to be printed in the Game's menu and String constants.
 *
 * <p>Created by james on 6/09/2016.
 */
public class Game {

  static final int MIN_PLAYERS = 3;
  static final int MAX_PLAYERS = 5;
  private static final int NUM_CARDS_PER_HAND = 8;
  static final String INSTRUCTIONS =
      "How to play:\n"
          + "1. A dealer (randomly chosen) shuffles the cards and deals each player 8\n"
          + "cards. Each player can look at their cards, but should not show them to\n"
          + "other players. The remaining card pack is placed face down on the table.\n\n"
          + "2. The player to the left of the dealer goes first by placing a mineral card on\n"
          + "the table. The player must state the mineral name, one of the five trump\n"
          + "categories (i.e., either Hardness, Specific Gravity, Cleavage, Crustal\n"
          + "Abundance, or Economic Value), and the top value of that category. For\n"
          + "example, a player placing the Glaucophane card may state “Glaucophane,\n"
          + "Specific Gravity, 3.2”\n\n"
          + "3. The player next to the left takes the next turn. This player must play a\n"
          + "mineral card that has a higher value in the trump category than the card\n"
          + "played by the previous player. In the case of the example of the\n"
          + "Glaucophane card above, the player must place a card that has a value for\n"
          + "specific gravity above 3.2. The game continues with the next player to the\n"
          + "left, and so on.\n\n"
          + "4. If a player does not have any mineral cards that are of higher value for the\n"
          + "specific trump category being played, then the player must pass and pick up\n"
          + "one card from the card pack on the table. The player then cannot play again\n"
          + "until all but one player has passed, or until another player throws a\n"
          + "supertrump card to change the trump category, as described below. A player\n"
          + "is allowed to pass even if they still hold cards that could be played. \n\n"
          + "5. If the player has a supertrump card (The Miner, The Geologist, The\n"
          + "Geophysicist, The Petrologist, The Mineralogist, The Gemmologist) they\n"
          + "may play this card at any of their turns. By placing a supertrump card, the\n"
          + "player changes the trump category according to the instructions on the\n"
          + "supertrump card. At this stage, any other player who had passed on the previous\n"
          + "round is now able to play again. If a player happens to hold both The\n"
          + "Geophysicist card and the Magnetite card in their hand, then that player can\n"
          + "place both cards together to win the round.\n\n"
          + "6. The game continues with players taking turns to play cards until all but one\n"
          + "player has passed. The last player then gets to lead out the next round and\n"
          + "chooses the trump category to be played.\n\n"
          + "7. The winner of the game is the first player to lose all of their cards. The\n"
          + "game continues until all but one player (i.e., the loser) has lost their cards.\n";
  static final String TRUMP_HIERARCHIES =
      "Information on trump categories:\n\n"
          + "Hardness: relates to Moh’s hardness scale of minerals from 1 to 10. Where a\n"
          + "range of values is presented, the highest value should be used.\n\n"
          + "Specific Gravity: in grams per cubic centimeter. Where a range of values is\n"
          + "presented, the highest value should be used.\n\n"
          + "Cleavage: refers to the number of cleavage planes and how well the planes\n"
          + "are typically expressed in the crystal. For example, “1 perfect, 2 poor” means\n"
          + "the mineral has 1 perfect cleavage plane, and 2 poor cleavage planes. The\n"
          + "order of ranking from lowest to highest is:\n"
          + "none < poor/none < 1 poor < 2 poor < 1 good < 1 good, 1 poor < 2\n"
          + "good < 3 good < 1 perfect < 1 perfect, 1 good < 1 perfect, 2 good < 2\n"
          + "perfect, 1 good < 3 perfect < 4 perfect < 6 perfect.\n\n"
          + "Crustal Abundance: is ranked from lowest to highest as:\n"
          + "ultratrace < trace < low < moderate < high < very high.\n\n"
          + "Economic Value: is ranked from lowest to highest as:\n"
          + "trivial < low < moderate < high < very high < I’m rich!\n";

  private Deck deck;

  private Card lastPlayedCard;
  private String currentTrumpCategory;

  private Player[] players;
  private Player currentPlayer;
  private ArrayList<Player> winners = new ArrayList<>();

  private int numPasses;
  private int numPlayers;
  private static int dealerIndex;

  public boolean isFirstTurn;
  private boolean humanPlayedCard = false;
  private boolean comboPlayed;
  private boolean isWon;

  public static Game currentGame;

  /**
   * Game constructor takes the desired number of players for the game, as well as a User's
   * username, and creates the appropriate number of human and nonHuman players. The userName is
   * assigned to the human player's name field. Relevant values are initialised to ensure the first
   * turn has its appropriate conditions followed.
   *
   * @param num represent the desired number of players for the current game's party.
   * @param userName is used to identify the human player/alert the user to their being up.
   */
  public Game(int num, String userName) {
    this.deck = GenerateDeckFromPLIST.buildDeck();
    numPlayers = num;
    players = generatePlayers(userName);
    isWon = false;
    numPasses = 0;
    isFirstTurn = true;
    comboPlayed = false;
    currentGame = this;
  }

  /**
   * Accesses the numPlayers field to determine the number of player objects to make, returns a
   * Player array with this many players, one of them being a human player, with the passed username
   * as their name.
   *
   * @param userName to be stored in human players name field.
   * @return Static array of players in the game.
   */
  private Player[] generatePlayers(String userName) {
    Player[] playerArray = new Player[numPlayers];
    playerArray[0] = new HumanPlayer(userName);
    for (int i = 1; i < numPlayers; ++i) {
      playerArray[i] = new NonHumanPlayer(i);
    }
    return playerArray;
  }

  /**
   * Generates random number within range (0 - numPlayers, (inclusive)), assigns the player at this
   * index in Players array as the dealer for the game.
   */
  public void assignDealer() {
    Random rn = new Random();
    dealerIndex = rn.nextInt(numPlayers);
  }

  /**
   * The rule cards are first removed from the deck and the deck is shuffled. The NUM_CARDS_PER_HAND
   * constant is accessed to determine how many cards to deal, the Player array field is accessed to
   * determine who to deal to. Dealing involves removing the a Card from the game's deck attribute
   * and placing it in the appropriate Player's hand attribute.
   */
  public void initialDeal() {
    deck.removeRuleCards();
    deck.shuffle();

    //For testing Combo mechanic.
    //        ArrayList<Card> userHand = new ArrayList<>();
    //        userHand.add(deck.getGeophys());
    //        userHand.add(deck.getMagnetite());
    //        userHand.addAll(deck.dealHand(NUM_CARDS_PER_HAND));
    //        players[0].setCurrentHand(userHand);
    //        for (int i = 1; i < players.length; i++) {
    //            ArrayList<Card> hand= deck.dealHand(NUM_CARDS_PER_HAND);
    //            players[i].setCurrentHand(hand);
    //        }

    for (Player player : this.players) {
      ArrayList<Card> hand = deck.dealHand(NUM_CARDS_PER_HAND);
      player.setCurrentHand(hand);
    }

    //        Dealer set as current player so that "player to the left of the dealer" goes first, as per the rules.
    this.currentPlayer = players[dealerIndex];
  }

  /**
   * Sets the current player to the next person in the Player array. If the previous player was the
   * last in the array, the current player is reset to the first.
   *
   * @return currentPlayer; the player who is to play the current turn.
   */
  public Player incrementCurrentPlayer() {
    int currentPlayerId = currentPlayer.getId();
    if (currentPlayerId == players.length - 1) {
      currentPlayer = players[0];
    } else {
      currentPlayer = players[currentPlayerId + 1];
    }
    return currentPlayer;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  Player getDealer() {
    return players[dealerIndex];
  }

  public Player[] getPlayers() {
    return players;
  }
  public Deck getDeck(){
    return this.deck;
  }

  boolean isWon() {
    return isWon;
  }

  boolean isHumanUp() {
    return this.getCurrentPlayer().isHuman();
  }

  /**
   * Check if the human has played a card to determine of the user turn menu should keep running.
   *
   * @return true if human played a card and wishes to end their turn, otherwise false
   */
  boolean getHumanPlayedCard() {
    return humanPlayedCard;
  }

  public void setHumanPlayedCard() {
    humanPlayedCard = true;
  }

  void resetHumanPlayedCard() {
    humanPlayedCard = false;
  }

  public Card getLastPlayedCard() {
    return lastPlayedCard;
  }

  public void setLastPlayedAttributes(Card lastPlayedCard) {
    this.lastPlayedCard = lastPlayedCard;
  }

  public String getCurrentTrumpCategory() {
    return currentTrumpCategory;
  }

  public String getTrumpChoiceFromTrumpCard(String trumpTitle) {
    String trumpChoice;
    switch (trumpTitle) {
      case "The Miner":
        trumpChoice = "Economic Value";
        break;
      case "The Petrologist":
        trumpChoice = "Crustal Abundance";
        break;
      case "The Gemmologist":
        trumpChoice = "Hardness";
        break;
      case "The Mineralogist":
        trumpChoice = "Cleavage";
        break;
      case "The Geophysicist":
        trumpChoice = "Specific Gravity";
        break;
      default:
        System.out.println("Error GetTrumpChoiceFromTrumpCard()");
        trumpChoice = "The Petrologist";
        break;
    }
    return trumpChoice;
  }

  public void setCurrentTrumpCategory(String currentTrumpCategory) {
    this.currentTrumpCategory = currentTrumpCategory;
  }

  /**
   * Handles the decision process for a nonHuman player playing the first turn of a round or the
   * game. will attempt to play combo, if it can not then any playable card. The attributes of the
   * card in play are updated appropriately.
   */
  void playFirstTurn() {
    //        When a robot plays first card of round:
    String trumpChoice;
    Card cardChoice;
    resetComboPlayed();
    //first turn of game
    if (isFirstTurn) {
      cardChoice = currentPlayer.playAnyMineralCard();
      trumpChoice = currentPlayer.getTrumpCategoryChoice();
    }
    //first turn of ROUND
    else {
      cardChoice = currentPlayer.playAnyCard();
      if (cardChoice.getCardType().equals("play") || cardChoice.isGeologist()) {
        trumpChoice = currentPlayer.getTrumpCategoryChoice();
      } else {
        trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
      }
    }
    setCurrentTrumpCategory(trumpChoice);
    setLastPlayedAttributes(cardChoice);
  }

  /**
   * Called only when a user is the first player of a round and is playing a mineral card or "The
   * Geologist". Prompts the user for a trump category choice then updated the value and category in
   * play.
   *
   * @param cardChoice the card from which the trump value is taken.
   * @param trumpChoice determines which of the cards attributes to use as a play value.
   */
  void playFirstTurn(int cardChoice, String trumpChoice) {
    //        When a user is playing a MineralCard Or 'The Geologist':
    Card chosenCard = currentPlayer.playCard(cardChoice);
    setCurrentTrumpCategory(trumpChoice);
    setLastPlayedAttributes(chosenCard);
    setHumanPlayedCard();
  }


  /**
   * Called only when a user plays a trump card that is not "The Geologist". Method will set the
   * current trump category based on card title.
   *
   * @param cardChoice the trump card from which the trump category in play is determined.
   */
  void playTrumpCard(int cardChoice) {
    //        When a user plays a Trump Card:
    Card chosenCard = currentPlayer.playCard(cardChoice);
    String trumpChoice = getTrumpChoiceFromTrumpCard(chosenCard.getTitle());
    setCurrentTrumpCategory(trumpChoice);
    setLastPlayedAttributes(chosenCard);
    setHumanPlayedCard();
    resetRoundTrump();
  }

  public void playTrumpCardGUI(Card cardChoice) {
    //        When a user plays a Trump Card with GUI:
    currentPlayer.playCard(cardChoice);
    String trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
    setCurrentTrumpCategory(trumpChoice);
    setLastPlayedAttributes(cardChoice);
    setHumanPlayedCard();
    resetRoundTrump();
  }

  /**
   * Handles the decision process for a nonHuman player playing any turn that is not the first turn
   * of a round or the game. will attempt to play combo, if it can not then any playable card. The
   * attributes of the card in play are updated appropriately. If the player has no playable cards,
   * they pass.
   */
  void playTurn() {
    if (currentPlayer.hasCombo()) {
      playCombo();
    } else {

      boolean haveCard = false;
      Card cardChoice = null;
      for (int i = 0; i < currentPlayer.getHand().size(); ++i) {
        if (currentPlayer.getHand().get(i).canPlayOn(lastPlayedCard, currentTrumpCategory)
            && !haveCard) {
          cardChoice = currentPlayer.playCard(i);
          haveCard = true;
        }
      }
      if (cardChoice != null && cardChoice.getCardType().equals("trump")) {
        String trumpChoice;
        if (cardChoice.isGeologist()) {
          trumpChoice = currentPlayer.getTrumpCategoryChoice();
        } else {
          trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
        }
        setCurrentTrumpCategory(trumpChoice);
        resetRoundTrump();
      }
      setLastPlayedAttributes(cardChoice);
    }
  }

  /**
   * Increments the current player and has player draw a card from deck if the deck is not empty.
   */
  public void passTurn() {
    currentPlayer.setHasPassed(true);
    if ((deck.getCards().size() != 0) && currentPlayer.getHand().size() != 0) {
      currentPlayer.drawCard(deck);
    }
  }

  private void resetNumPasses() {
    numPasses = 0;
  }

  public void incrementNumPasses() {
    numPasses += 1;
  }

  /** Resets the number of passes and replaces players who hav passes and not yet won */
  public void resetRound() {
    resetNumPasses();
    for (Player player : players) {
      if (!winners.contains(player)) {
        player.setHasPassed(false);
      }
    }
  }

  /** Replaces the players in the round after someone plays a Supertrump card */
  public void resetRoundTrump() {
    resetNumPasses();
    for (Player player : players) {
      player.setHasPassed(false);
    }
  }

  boolean checkWinner() {
    return getCurrentPlayer().getHand().size() == 0;
  }

  ArrayList<Player> getWinners() {
    return winners;
  }

  /** Adds a player to the winner list. Will set value to end game if all but one player in list */
  void updateWinners() {
    if (!winners.contains(currentPlayer)) {
      winners.add(currentPlayer);
    }
    if (winners.size() == getPlayers().length - 1) {
      setIsWon();
    }
  }

  private void setIsWon() {
    isWon = true;
  }

  public boolean isFirstTurn() {
    return isFirstTurn;
  }

  public void setFirstTurn(boolean firstTurn) {
    isFirstTurn = firstTurn;
  }

  /**
   * Checks how many of the players in the game have passed. If this number is one less than the
   * number of players who have not already won, the round is reset.
   *
   * @return true if number of passes is one less than number of players.
   */
  public boolean isNewRound() {
    return (numPasses == numPlayers - 1 - winners.size());
  }

  /**
   * Calls appropriate Player subclass methods for removing the two combo cards from player's hand.
   * Resets the round, as per the game rules, to have combo player go again.
   */
  public void playCombo() {
    this.lastPlayedCard = currentPlayer.playCombo();
    comboPlayed = true;
    resetRound();
  }

  public boolean comboPlayed() {
    return comboPlayed;
  }

  public void resetComboPlayed() {
    comboPlayed = false;
  }

  public Player getNextPlayer() {
    Player nextPlayer;
    int currentPlayerId = currentPlayer.getId();
    if (currentPlayerId == players.length - 1) {
      nextPlayer = players[0];
    } else {
      nextPlayer = players[currentPlayerId + 1];
    }
    return nextPlayer;
  }

  /**
   * For gui when human plays a card
   * @param cardChoice  Card object represent the card to be played
   * @param trumpChoice the trump choice to be put in play
     */
  public void playFirstTurn(Card cardChoice, String trumpChoice) {
    currentPlayer.playCard(cardChoice);
    setCurrentTrumpCategory(trumpChoice);
    setLastPlayedAttributes(cardChoice);
//    setHumanPlayedCard();

  }

  public void playTurn(Card cardChoice)
  {
    currentPlayer.playCard(cardChoice);
    setLastPlayedAttributes(cardChoice);

  }

}
