package GUI;

import game.Game;
import player.Player;

import javax.swing.*;
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
        Player currentPlayer = newGame.getCurrentPlayer();

        if (newGame.isNewRound()) {
            Player lastToPlay = newGame.getLastPlayer();
            System.out.println("\nRound Won by: " + lastToPlay.getName());
            newGame.setCurrentPlayer(lastToPlay);
            currentPlayer = newGame.getCurrentPlayer();
            if (!currentPlayer.isHuman()){
                PlayRobotTurn.playTurn(gui);
                newGame.incrementCurrentPlayer();

            }
            else {
                PlayHumanTurn.enableUserButtons(gui);
            }

        }
        else {
            newGame.incrementCurrentPlayer();
        }

        currentPlayer = newGame.getCurrentPlayer();
        if (!newGame.winners.contains(currentPlayer)) {
            if (!currentPlayer.getHasPassed()) {
                if (!currentPlayer.isHuman()){
                    PlayRobotTurn.playTurn(gui);
                }
                else{
                    PlayHumanTurn.enableUserButtons(gui);
                }
                if (newGame.getLastPlayedCard() != null) {
                    updateLastPlayedCard(newGame, gui);
                }

            }
            else{
                System.out.println(currentPlayer.getName() + " has already passed!");
//                newGame.incrementCurrentPlayer();
            }
        }else{
            System.out.println(currentPlayer.getName() + " has already won!");
//            newGame.incrementCurrentPlayer();
        }
        UpdateLabels.updateNextPlayerLabel(newGame, gui);

    }

    public static void updateLastPlayedCard(Game newGame, MineralST_GUI gui) {
        JPanel cardPanel = new JPanel();
        JLabel cardLabel = new JLabel();
        ImageIcon cardIcon = new ImageIcon("src/GUI/images/cards/" + newGame.getLastPlayedCard().getFileName());
        cardLabel.setIcon(cardIcon);
        cardPanel.add(cardLabel);
        gui.lastPlayedCard.removeAll();
        gui.lastPlayedCard.add(cardPanel);
    }

}
