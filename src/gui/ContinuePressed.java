package gui;

import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the flow of the game. The Gui equivalent of Part 1's RunGame class.
 * Called every time the user presses "Continue" on the Play Game screen
 * Created by james on 19/10/2016. */
public class ContinuePressed implements ActionListener {
  MineralST_GUI gui;

  public ContinuePressed(MineralST_GUI gui) {
    this.gui = gui;
  }
  /**
   * Increments current Player, checks if player has won the Game, checks if player has won the round
   * Determines whether a human or robot is up to play and calls appropriate function.
   * Updates labels if someone played a card
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Game newGame = Game.currentGame;
    Player currentPlayer = newGame.getCurrentPlayer();

    if (newGame.isNewRound()) {
      Player lastToPlay = newGame.getLastPlayer();
      JOptionPane.showMessageDialog(
          null,
          "Round Won by: " + lastToPlay.getName(),
          "Round Won!",
          JOptionPane.INFORMATION_MESSAGE);
      System.out.println("\nRound Won by: " + lastToPlay.getName());

      if (newGame.getWinners().contains(lastToPlay)) {
        newGame.incrementCurrentPlayer();
      } else {
        newGame.setCurrentPlayer(lastToPlay);
      }
      currentPlayer = newGame.getCurrentPlayer();
      if (!currentPlayer.isHuman()) {
        PlayRobotTurn.playTurn(gui);
        newGame.incrementCurrentPlayer();


      } else {
        if (!newGame.getWinners().contains(newGame.getCurrentPlayer())) {
          PlayHumanTurn.enableUserButtons(gui);
        }
      }

    }
    else if (newGame.comboPlayed()){
      System.out.println("in combo played");
      PlayRobotTurn.playTurn(gui);
      updateLastPlayedCard(newGame, gui);
      UpdateLabels.updateLastPlayedCardGUI(gui, newGame);
    }
    else {
      newGame.incrementCurrentPlayer();
    }

    currentPlayer = newGame.getCurrentPlayer();
    if (!newGame.winners.contains(currentPlayer)) {
      if (!currentPlayer.getHasPassed()) {
        if (!currentPlayer.isHuman()) {
          PlayRobotTurn.playTurn(gui);
        } else {
          PlayHumanTurn.enableUserButtons(gui);
        }
        if (newGame.getLastPlayedCard() != null) {
          updateLastPlayedCard(newGame, gui);
        }

      } else {
        System.out.println(currentPlayer.getName() + " has already passed!");
      }
    } else {
      System.out.println(currentPlayer.getName() + " has already won!");
    }
    UpdateLabels.updateNextPlayerLabel(newGame, gui);
  }

  /**
   * Displays the last played card in the bottom right hand corner of the Play Game screen
   * @param newGame The current Game object
   * @param gui The custom Mineral Supertrumps gui object
   */
  public static void updateLastPlayedCard(Game newGame, MineralST_GUI gui) {
    JPanel cardPanel = new JPanel();
    JLabel cardLabel = new JLabel();
    ImageIcon cardIcon =
        new ImageIcon("src/GUI/images/cards/" + newGame.getLastPlayedCard().getFileName());
    cardLabel.setIcon(cardIcon);
    cardPanel.add(cardLabel);
    gui.lastPlayedCard.removeAll();
    gui.lastPlayedCard.add(cardPanel);
  }
}
