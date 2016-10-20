package GUI;

import game.Game;
import player.Player;

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
        Game newGame = Game.currentGame;
        newGame.incrementCurrentPlayer();
        Player currentPlayer = newGame.getCurrentPlayer();
        gui.currentPlayerLabel.setText("Current Player: " + newGame.getCurrentPlayer().getName());
        if (!currentPlayer.isHuman()){
            PlayRobotTurn.playTurn(gui);
        }
        else{
            PlayHumanTurn.enableUserButtons(gui);
//            PlayHumanTurn.playTurn(gui);
        }

    }
}
