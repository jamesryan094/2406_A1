package GUI;

import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by james on 19/10/2016.
 */
public class PrepareGame {


    public static void buildMineralSTGame(MineralST_GUI gui, int numPlayers, String userName) {
        MineralST_GUI mineralSTgui = gui;
        JPanel mineralSTContainer = gui.MineralST;
        CardLayout mineralSTLayout = (CardLayout) mineralSTContainer.getLayout();
        Game newGame = new Game(numPlayers, userName);
        populatePlayers(gui, newGame);
        generateDeck(gui);
        mineralSTLayout.show(mineralSTContainer, "playGameCard");
    }

    private static void populatePlayers(MineralST_GUI gui, Game newGame) {
        Player[] players = newGame.getPlayers();
        int numPlayers = newGame.getPlayers().length;
        for(int i = 0; i<numPlayers; ++i){
            ImageIcon playerIconImg = new ImageIcon("src/GUI/images/playerIcons/playerIcon_" + i + ".jpg");
            JLabel playerLabel = new JLabel(players[i].getName());
            playerLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            playerLabel.setIcon(playerIconImg);
            gui.players.add(playerLabel);
        }
    }

    private static void generateDeck(MineralST_GUI gui) {

    }

}
