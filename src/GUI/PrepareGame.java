package GUI;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by james on 19/10/2016.
 */
public class PrepareGame {


    public static void buildMineralSTGame(MineralST_GUI gui, int numPlayers, String userName) {
        MineralST_GUI mineralSTgui = gui;
        JPanel mineralSTContainer = gui.MineralST;
        CardLayout mineralSTLayout = (CardLayout) mineralSTContainer.getLayout();
        Game newGame = new Game(numPlayers, userName);
        newGame.assignDealer();
        newGame.initialDeal();
        newGame.setFirstTurn(true);
        newGame.resetRound();
        populatePlayers(gui, newGame);
        generateHandIcons(gui, newGame);
//        newGame.incrementCurrentPlayer();
        gui.currentPlayerLabel.setText("Dealer: " + newGame.getCurrentPlayer().getName());
        gui.nextPlayerLabel.setText("Next Player: " + newGame.getNextPlayer().getName());
        mineralSTLayout.show(mineralSTContainer, "playGameCard");
//        return newGame;
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

    public static void generateHandIcons(MineralST_GUI gui, Game newGame) {
        //For each card in the human player's hand
        gui.userCards.removeAll();
        ArrayList<Card> userHand = newGame.getPlayers()[0].getHand();
        for(int i = 0; i < userHand.size();++i){
            JPanel cardPanel = new JPanel();
            JLabel cardLabel = new JLabel();
//            cardLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
//            cardLabel.setHorizontalTextPosition(SwingConstants.CENTER);
//            System.out.println("src/GUI/images/cards/" + userHand.get(i).getFileName());
            ImageIcon cardIcon = new ImageIcon("src/GUI/images/cards/" + userHand.get(i).getFileName());
            cardLabel.setIcon(cardIcon);
            cardPanel.add(cardLabel);
            cardPanel.setName(userHand.get(i).getTitle());
            gui.userCards.add(cardPanel);
            gui.userCards.revalidate();
            gui.userCards.repaint();
        }
    }

}
