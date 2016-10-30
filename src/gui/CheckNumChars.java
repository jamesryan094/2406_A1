package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * For Prepare Game screen: Check the user has enter a username that is not just whitespace Created
 * by james on 18/10/2016.
 */
class CheckNumChars implements KeyListener {
  private JTextField usernameTextField;
  private JRadioButton threePlayer;
  private JRadioButton fourPlayer;
  private JRadioButton fivePlayer;

  CheckNumChars(JTextField usernameTextField, JRadioButton[] numPlayersButtonGroup) {
    this.usernameTextField = usernameTextField;
    threePlayer = numPlayersButtonGroup[0];
    fourPlayer = numPlayersButtonGroup[1];
    fivePlayer = numPlayersButtonGroup[2];
  }

  /**
   * Check length of username field every time a key is released. If the stripped version of the
   * user input is not blank, enable the number of players radio button section
   *
   * @param e The event created after a user presses a key, the moment the key is no longer active
   *     the event is generated
   */
  @Override
  public void keyReleased(KeyEvent e) {
    String currentWord = usernameTextField.getText();
    if (wordNotBlank(currentWord)) {
      threePlayer.setEnabled(true);
      fourPlayer.setEnabled(true);
      fivePlayer.setEnabled(true);
    } else {
      threePlayer.setEnabled(false);
      fourPlayer.setEnabled(false);
      fivePlayer.setEnabled(false);
    }
  }

  /**
   * Take String and strip it. Checks length of stripped string.
   *
   * @param userName User inout to be used as the username. must not be blank
   * @return true if not blank, otherwise false
   */
  static boolean wordNotBlank(String userName) {
    String strippedWord = userName.replaceAll("\\s+", "");
    return (strippedWord.length() > 0);
  }

  //Following function overridden so that KeyListener may be implemented
  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {}
}
