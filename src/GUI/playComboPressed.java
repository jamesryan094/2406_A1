package GUI;

import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 23/10/2016.
 */
public class playComboPressed implements ActionListener {

    public playComboPressed(MineralST_GUI gui) {


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game currentGame = Game.currentGame;
        Player currentPlayer = currentGame.getCurrentPlayer();
        if (!currentGame.isFirstTurn) {
            if (currentPlayer.hasCombo()){
//                Todo: polish
                currentPlayer.playCombo();
            }else{
                JOptionPane.showMessageDialog(null, "Your hand does not contain the combo!", "Hold It!", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "You're not allowed to play the combo on the first turn! Why would you even?", "Hold It!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
