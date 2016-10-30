//Todo: Refactor into two classes, one for error checking, one for preparing a new game.
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * For Prepare Game screen, after selecting "Play Game": Ensure user has entered all necessary,
 * valid information to create a new game object. Created by james on 18/10/2016.
 */
class CheckReadytoPlay implements ActionListener {
  private JRadioButton[] numPlayersGroup;
  private final JPanel testPanel;
  private JPanel playGame;
  private JPanel mineralSTContainer;
  private CardLayout mineralSTLayout;
  MineralST_GUI gui;

  CheckReadytoPlay(MineralST_GUI mineralST_gui) {
    numPlayersGroup = mineralST_gui.numPlayersButtonGroup;
    mineralSTContainer = mineralST_gui.mineralST;
    mineralSTLayout = (CardLayout) mineralSTContainer.getLayout();
    this.testPanel = mineralST_gui.testPanel;
    gui = mineralST_gui;
    this.playGame = gui.playGame;
  }

  /**
   * Called when "play Game" button is pressed from Prepare Game screen. Checks user has entered a
   * username and selected a desired number of players. If valid, a new game object is created and
   * the user is taken to the Play Game screen
   *
   * @param e the event created upon pressing "Play Game".
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    int numPlayers = -1;
    for (int i = 0; i < numPlayersGroup.length; ++i) {
      if (numPlayersGroup[i].isSelected()) {
        numPlayers = i + 3;
      }
    }
    if (CheckNumChars.wordNotBlank(gui.usernameTextField.getText())) {
      PrepareGame.buildMineralSTGame(gui, numPlayers, gui.usernameTextField.getText());
    } else {
      JOptionPane.showMessageDialog(
          null, "You must enter a username to start a game.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
