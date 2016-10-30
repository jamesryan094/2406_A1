package gui;

import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Called when a user presses "Play Combo" option on the Play Game screen.
 * Created by james on 23/10/2016.
 */
class playComboPressed implements ActionListener {

  private final MineralST_GUI gui;

  playComboPressed(MineralST_GUI gui) {
    this.gui = gui;
  }

  /**
   * If user has combo, combo is played, the last played card is set to magnetite and the user
   * begins a new round.
   *
   * @param e the even created upon pressing "Play Combo" button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Game currentGame = Game.currentGame;
    Player currentPlayer = currentGame.getCurrentPlayer();
    if (!currentGame.isFirstTurn) {
      if (currentPlayer.hasCombo()) {
        currentGame.playCombo();
        ContinuePressed.updateLastPlayedCard(currentGame, gui);
        UpdateLabels.updateLastPlayedCardGUI(gui, currentGame);
        PrepareGame.generateHandIcons(gui, currentGame);
        JOptionPane.showMessageDialog(
            null,
            currentGame.getCurrentPlayer().getName()
                + " has played the combo! They get to go again!",
            "Combo Played!",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(
            null, "Your hand does not contain the combo!", "Hold It!", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(
          null,
          "You're not allowed to play the combo on the first turn!",
          "Hold It!",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
