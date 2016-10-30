package gui;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Human players have little restriction on what they can try in a game however, have the same
 * restrictions for what is considered valid in a game as a nonhuman player.
 * Created by james on 20/10/2016. */
public class PlayHumanTurn {


  public static void enableUserButtons(MineralST_GUI gui) {
    gui.continueButton.setEnabled(false);
    gui.playCardButton.setEnabled(true);
    gui.playComboButton.setEnabled(true);
    gui.passButton.setEnabled(true);
  }

  /**
   * Once a card has been selected, playTurn determines validity of card choice, and what do do with said card
   * based on attributes set in the current Game object
   * @param gui The custom Mineral Supertrumps gui object
     */
  public static void playTurn(MineralST_GUI gui) {
    Game newGame = Game.currentGame;
    Player currentPlayer = newGame.getCurrentPlayer();
    Card cardChoice = getSelectedCard(gui, newGame);

    boolean cardPlayed = true;

    if (newGame.isFirstTurn()) {
      if (cardChoice.getCardType().equals("trump")) {
        JOptionPane.showMessageDialog(
            null,
            "You can not start the game with a trump card!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
      } else {
        String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
        newGame.playFirstTurn(cardChoice, trumpChoice);
      }
    } else if (newGame.isNewRound() || newGame.comboPlayed()) {
      newGame.resetComboPlayed();
      if (cardChoice.getCardType().equals("trump")) {
        if (cardChoice.isGeologist()) {
          String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
          newGame.playFirstTurn(cardChoice, trumpChoice);
          newGame.resetRoundTrump();
        } else {
          newGame.playTrumpCardGUI(cardChoice);
        }
        JOptionPane.showMessageDialog(
                null,
                newGame.getCurrentPlayer().getName() + " played a supertrump!" +
                        "\nEverybody is back in the round!",
                "Supertrump Played!",
                JOptionPane.INFORMATION_MESSAGE);
      } //CardType = mineral
      else {
        String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
        newGame.playFirstTurn(cardChoice, trumpChoice);
      }
      newGame.resetRound();
      //            newGame.incrementCurrentPlayer();
    } else {
      if (cardChoice.canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())) {
        if (cardChoice.getCardType().equals("trump") && !cardChoice.isGeologist()) {
          newGame.playTrumpCardGUI(cardChoice);
        } else if (cardChoice.isGeologist()) {
          String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
          newGame.playFirstTurn(cardChoice, trumpChoice);
          newGame.resetRoundTrump();
          JOptionPane.showMessageDialog(
                  null,
                  newGame.getCurrentPlayer().getName() + " played a supertrump!" +
                          "\nEverybody is back in the round!",
                  "Supertrump Played!",
                  JOptionPane.INFORMATION_MESSAGE);
        }
        //chose mineral card that can play on last played
        newGame.playTurn(cardChoice);
        newGame.setHumanPlayedCard();
      } else {
        cardPlayed = false;
        JOptionPane.showMessageDialog(
            null,
            "You can not play that card\nMake sure you pay attention to the current trump value and category.",
            "Hold It!",
            JOptionPane.ERROR_MESSAGE);
      }
    }
    if (cardPlayed) {
      newGame.setLastPlayer(newGame.getCurrentPlayer());
      PrepareGame.generateHandIcons(gui, newGame);
      ContinuePressed.updateLastPlayedCard(newGame, gui);
      UpdateLabels.updateLastPlayedCardGUI(gui, newGame);
      disableUserButtons(gui);
      if (newGame.checkWinner()) {
        if (!newGame.getWinners().contains(currentPlayer)) {
          newGame.winners.add(currentPlayer);
          JOptionPane.showMessageDialog(
              null,
              newGame.getCurrentPlayer().getName() + " has been added to the Winners List!",
              "Winner!",
              JOptionPane.INFORMATION_MESSAGE);
          System.out.println(currentPlayer.getName() + " has been added to the winners list!");
        }
        if (newGame.getWinners().size() == (newGame.getPlayers().length - 1)) {
          JOptionPane.showMessageDialog(
              null,
              "Game over! Well done everyone!",
              "Game Over!",
              JOptionPane.INFORMATION_MESSAGE);
          new ReturnToMenu(gui.mineralST).showMainMenu();
        }
      }
    }
  }

  /**
   * Returns a reference to the card that is currently being displayed in the 'Cards' section of the user control panel
   * @param gui The custom Mineral Supertrumps gui object
   * @param newGame The current Game object
   * @return Card: the last plated card
     */
  private static Card getSelectedCard(MineralST_GUI gui, Game newGame) {
    Component[] userCardComponents = gui.userCards.getComponents();
    Card cardChoice;
    for (Component component : userCardComponents) {
      if (component.isVisible()) {
        JPanel cardPanel = (JPanel) component;
        cardChoice = getCard(newGame.getCurrentPlayer(), cardPanel);
        return cardChoice;
      }
    }
    System.out.println("Something has gone terribly wrong (gui.playHumanTurn.getSelectedCard())");
    return null;
  }

  /**
   * Creates and displays a dialogue box with a dropdown menu from which the user selects the trump category to play
   * @param gui The custom Mineral Supertrumps gui object
   * @param newGame The current Game object
   * @return String: The user's trump category selection
     */
  private static String getTrumpCategoryFromUser(MineralST_GUI gui, Game newGame) {
    String[] trumps = {
      "Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"
    };
    String trumpChoice =
        (String)
            JOptionPane.showInputDialog(
                null,
                "Select a Trump Category!",
                "Select Category",
                JOptionPane.QUESTION_MESSAGE,
                null,
                trumps, // Array of choices
                trumps[0]); // Initial choice
    return trumpChoice;
  }

  /**
   * Returns card object from players hand that corresponds with the card currently being displayed
   * @param currentPlayer The player from who's hand the card is being drawn.
   * @param cardChoice The selected card
   * @return Card: the selected Card
     */
  private static Card getCard(Player currentPlayer, JPanel cardChoice) {
    for (Card card : currentPlayer.getHand()) {
      if (card.getTitle().equals(cardChoice.getName())) {
        int cardIndex = currentPlayer.getHand().indexOf(card);
        return currentPlayer.getHand().get(cardIndex);
      }
    }
    return null;
  }

  /**
   * When user chooses to pass: increment player, draw card if possible
   * @param gui The custom Mineral Supertrumps gui object
     */
  public static void passTurn(MineralST_GUI gui) {
    Game newGame = Game.currentGame;
    System.out.println(newGame.getCurrentPlayer().getName() + " chose to Pass");
    newGame.passTurn();
    newGame.incrementNumPasses();
    PrepareGame.generateHandIcons(gui, newGame);
    disableUserButtons(gui);

  }

  /**
   * When the user is not the current player, disable the user control buttons, enable the continue button.
   * @param gui The custom Mineral Supertrumps gui object
     */
  private static void disableUserButtons(MineralST_GUI gui) {
    gui.continueButton.setEnabled(true);
    gui.playCardButton.setEnabled(false);
    gui.playComboButton.setEnabled(false);
    gui.passButton.setEnabled(false);
  }
}
