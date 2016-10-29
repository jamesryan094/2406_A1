package gui;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;

/** Created by james on 20/10/2016. */
public class PlayRobotTurn {

  public static void playTurn(MineralST_GUI gui) {
    Game newGame = Game.currentGame;
    if (newGame.isNewRound() || newGame.isFirstTurn() || newGame.comboPlayed()) {
      System.out.println(newGame.getCurrentPlayer().getName() + " Is New Round");
      playFirstTurn(newGame, gui);
      newGame.resetRound();
      newGame.resetComboPlayed();
    } else {
      if (newGame
          .getCurrentPlayer()
          .hasPlayableCards(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())) {
        System.out.println(newGame.getCurrentPlayer().getName() + " Not a new round");
        playNotFirstTurn(gui);
      } else {
        newGame.passTurn();
        newGame.incrementNumPasses();

        JOptionPane.showMessageDialog(
                null,
                newGame.getCurrentPlayer().getName() + " chose to pass!",
                "Pass!",
                JOptionPane.INFORMATION_MESSAGE);
        System.out.println(newGame.getCurrentPlayer().getName() + " chose to pass");
//        UpdateLabels.setPlayerPassed(gui, newGame);
      }
    }
    if (newGame.checkWinner()) {
      if (!newGame.getWinners().contains(newGame.getCurrentPlayer())) {
        newGame.winners.add(newGame.getCurrentPlayer());
        JOptionPane.showMessageDialog(
            null,
            newGame.getCurrentPlayer().getName() + " has been added to the Winners List!",
            "Winner!",
            JOptionPane.INFORMATION_MESSAGE);
        System.out.println(
            newGame.getCurrentPlayer().getName() + " has been added to the winners list!");
      }
      if (newGame.getWinners().size() == (newGame.getPlayers().length - 1)) {
        JOptionPane.showMessageDialog(
            null, "Game over! Well done everyone!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        new ReturnToMenu(gui.mineralST).showMainMenu();
      }
    }
  }

  private static void playNotFirstTurn(MineralST_GUI gui) {
    Game newGame = Game.currentGame;
    Player currentPlayer = newGame.getCurrentPlayer();
    if (currentPlayer.hasCombo()) {
      newGame.playCombo();
      ContinuePressed.updateLastPlayedCard(newGame, gui);
      UpdateLabels.updateLastPlayedCardGUI(gui, newGame);
      JOptionPane.showMessageDialog(
              null, newGame.getCurrentPlayer().getName() + " has played the combo! They get to go again!", "Combo Played!", JOptionPane.INFORMATION_MESSAGE);
    } else {

      boolean haveCard = false;
      Card cardChoice = null;
      for (int i = 0; i < currentPlayer.getHand().size(); ++i) {
        if (currentPlayer
                .getHand()
                .get(i)
                .canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())
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
          trumpChoice = newGame.getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
        }
        newGame.setCurrentTrumpCategory(trumpChoice);
        newGame.resetRoundTrump();
        JOptionPane.showMessageDialog(
                null,
                newGame.getCurrentPlayer().getName() + " played a supertrump!" +
                        "\nEverybody is back in the round!",
                "Supertrump Played!",
                JOptionPane.INFORMATION_MESSAGE);
      }
      newGame.setLastPlayedAttributes(cardChoice);
      newGame.setLastPlayer(newGame.getCurrentPlayer());
      UpdateLabels.updateLastPlayedCardGUI(gui, newGame);
    }
  }

  private static void playFirstTurn(Game newGame, MineralST_GUI gui) {
    String trumpChoice;
    Card cardChoice;
//    newGame.resetComboPlayed();
    //first turn of game
    if (newGame.isFirstTurn) {
      cardChoice = newGame.getCurrentPlayer().playAnyMineralCard();
      trumpChoice = newGame.getCurrentPlayer().getTrumpCategoryChoice();
      newGame.setFirstTurn(false);
    }
    //first turn of ROUND
    else {
      cardChoice = newGame.getCurrentPlayer().playAnyCard();
      if (cardChoice.getCardType().equals("play") || cardChoice.isGeologist()) {
        trumpChoice = newGame.getCurrentPlayer().getTrumpCategoryChoice();
      } else {
        trumpChoice = newGame.getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
      }
    }
    newGame.setCurrentTrumpCategory(trumpChoice);
    newGame.setLastPlayedAttributes(cardChoice);
    newGame.setLastPlayer(newGame.getCurrentPlayer());
    System.out.println(newGame.getCurrentTrumpCategory());
    System.out.println(
        newGame
            .getLastPlayedCard()
            .getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
    UpdateLabels.updateLastPlayedCardGUI(gui, newGame);
  }
}