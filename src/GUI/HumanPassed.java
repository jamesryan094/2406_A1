package GUI;

import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by james on 20/10/2016.
 */
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
