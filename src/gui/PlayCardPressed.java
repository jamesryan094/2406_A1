package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Called when a user presses "Play Card"on Play Game screen.
 *  Created by james on 21/10/2016. */
class PlayCardPressed implements ActionListener {
  MineralST_GUI gui;

  PlayCardPressed(MineralST_GUI gui) {
    this.gui = gui;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PlayHumanTurn.playTurn(gui);
  }
}
