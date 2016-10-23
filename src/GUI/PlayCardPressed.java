package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Created by james on 21/10/2016. */
public class PlayCardPressed implements ActionListener {
  MineralST_GUI gui;

  public PlayCardPressed(MineralST_GUI gui) {
    this.gui = gui;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PlayHumanTurn.playTurn(gui);
  }
}
