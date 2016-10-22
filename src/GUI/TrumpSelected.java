package GUI;

import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 21/10/2016.
 */
public class TrumpSelected implements ActionListener {
    String trumpChoice;

    public TrumpSelected(JComboBox trumpSelection) {
        trumpChoice = String.valueOf(trumpSelection.getSelectedItem());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTrumpCategory();

    }

    private void updateTrumpCategory() {
        Game.currentGame.setCurrentTrumpCategory(trumpChoice);
    }
}
