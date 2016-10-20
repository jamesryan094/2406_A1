package GUI;

import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 19/10/2016.
 */
public class ContinuePressed implements ActionListener {
    MineralST_GUI gui;

    public ContinuePressed(MineralST_GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game.currentGame.incrementCurrentPlayer();
        gui.currentPlayerLabel.setText("Current Player: " + Game.currentGame.getCurrentPlayer().getName());
    }
}
