package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Called when user presses "Pass"on Play Game screen
 * Created by james on 20/10/2016. */
public class HumanPassed implements ActionListener {
  MineralST_GUI gui;

  public HumanPassed(MineralST_GUI gui) {
    this.gui = gui;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PlayHumanTurn.passTurn(gui);
  }
}
