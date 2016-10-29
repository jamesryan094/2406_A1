package gui;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by james on 23/10/2016. */
public class playComboPressed implements ActionListener {

  private final MineralST_GUI gui;

  public playComboPressed(MineralST_GUI gui) {
    this.gui = gui;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Game currentGame = Game.currentGame;
    Player currentPlayer = currentGame.getCurrentPlayer();
    if (!currentGame.isFirstTurn) {
      if (currentPlayer.hasCombo()) {
        //                Todo: polish
//        Card magnetite = currentPlayer.playCombo();
        currentGame.playCombo();
        ContinuePressed.updateLastPlayedCard(currentGame, gui);
        UpdateLabels.updateLastPlayedCardGUI(gui, currentGame);
        PrepareGame.generateHandIcons(gui, currentGame);
        JOptionPane.showMessageDialog(
                null, currentGame.getCurrentPlayer().getName() + " has played the combo! They get to go again!", "Combo Played!", JOptionPane.INFORMATION_MESSAGE);
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
