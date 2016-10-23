//Todo: Refactor into two classes, one for error checking, one for preparing a new game.
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 18/10/2016.
 */
public class CheckReadytoPlay implements ActionListener {
    JRadioButton[] numPlayersGroup;
    private final JPanel testPanel;
    private JPanel playGame;
    private JPanel mineralSTContainer;
    private CardLayout mineralSTLayout;
    MineralST_GUI gui;

    public CheckReadytoPlay(MineralST_GUI mineralST_gui) {
        numPlayersGroup = mineralST_gui.numPlayersButtonGroup;
        mineralSTContainer = mineralST_gui.MineralST;
        mineralSTLayout = (CardLayout) mineralSTContainer.getLayout();
        this.testPanel = mineralST_gui.testPanel;
        gui = mineralST_gui;
        this.playGame = gui.playGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numPlayers = -1;
        for(int i = 0; i<numPlayersGroup.length;++i){
            if (numPlayersGroup[i].isSelected()){
                numPlayers = i + 3;
            }
        }
        if (CheckNumChars.wordNotBlank(gui.usernameTextField.getText())) {
            System.out.println("You Selected " + (numPlayers));
            PrepareGame.buildMineralSTGame(gui, numPlayers, gui.usernameTextField.getText());
        }
        else{
      JOptionPane.showMessageDialog(
          null, "You must enter a username to start a game.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
