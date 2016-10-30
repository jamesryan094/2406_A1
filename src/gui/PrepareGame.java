package gui;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Takes information for Prepare Game screen and builds new Game object with it.
 * Shows the Play Game Screen
 * Created by james on 19/10/2016. */
class PrepareGame {

  /**
   * Takes information for Prepare Game screen and builds new Game object with it.
   * Shows the Play Game Screen
   * @param gui The custom Mineral Supertrumps gui object
   * @param numPlayers the radioButton selection form the Prepare Game screen
   * @param userName the text input from the Prepare Game screen
     */
  static void buildMineralSTGame(MineralST_GUI gui, int numPlayers, String userName) {
    MineralST_GUI mineralSTgui = gui;
    JPanel mineralSTContainer = gui.mineralST;
    CardLayout mineralSTLayout = (CardLayout) mineralSTContainer.getLayout();
    Game newGame = new Game(numPlayers, userName);
    newGame.assignDealer();
    newGame.initialDeal();
    newGame.setFirstTurn(true);
    newGame.resetRound();
    emptyScreen(gui, newGame);
    populatePlayers(gui, newGame);
    generateHandIcons(gui, newGame);
    gui.currentValueLabel.setVisible(false);
    gui.currentPlayerLabel.setText("Dealer: " + newGame.getCurrentPlayer().getName());
    gui.nextPlayerLabel.setText("Next Player: " + newGame.getNextPlayer().getName());
    mineralSTLayout.show(mineralSTContainer, "playGameCard");
  }

  /**
   * Removes all components from the Play Game screen
   * Called when starting a new game to ensure that, one a game has been complete, another can be started seamlessly
   * @param gui The custom Mineral Supertrumps gui object
   * @param newGame The current Game object
     */
  private static void emptyScreen(MineralST_GUI gui, Game newGame) {
    for (Component component : gui.players.getComponents()) {
      if (component != null) {
        gui.players.remove(component);
      }
    }
    for (Component component : gui.lastPlayedCard.getComponents()) {
      if (component != null) {
        gui.lastPlayedCard.remove(component);
      }
    }
    UpdateLabels.resetLabels(gui);
  }

  /**
   * Takes the num players radio button choice from Prepare Game screen and generate the appropriate number of
   * player icons, with corresponding user names displayed below
   * @param gui The custom Mineral Supertrumps gui object
   * @param newGame The current Game object
   */
  private static void populatePlayers(MineralST_GUI gui, Game newGame) {
    Player[] players = newGame.getPlayers();
    int numPlayers = newGame.getPlayers().length;
    for (int i = 0; i < numPlayers; ++i) {
      ImageIcon playerIconImg =
          new ImageIcon("src/GUI/images/playerIcons/playerIcon_" + i + ".jpg");
      JLabel playerLabel = new JLabel(players[i].getName());
      playerLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
      playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
      playerLabel.setIcon(playerIconImg);
      playerLabel.setName(players[i].getName());
      gui.players.add(playerLabel);
    }
  }

  /**
   * Iterates through the human user's hand and generates and image for each card.
   * Images is places in the "Cards" section of the screen
   * @param gui The custom Mineral Supertrumps gui object
   * @param newGame The current Game object
     */
  static void generateHandIcons(MineralST_GUI gui, Game newGame) {
    //For each card in the human player's hand
    gui.userCards.removeAll();
    ArrayList<Card> userHand = newGame.getPlayers()[0].getHand();
    if (userHand.size() == 0) {
      JPanel cardPanel = new JPanel();
      JLabel cardLabel = new JLabel();
      cardLabel.setBackground(Color.RED);
      cardPanel.add(cardLabel);
      gui.userCards.add(cardPanel);
      gui.userCards.revalidate();
      gui.userCards.repaint();
    } else {
      for (int i = 0; i < userHand.size(); ++i) {
        JPanel cardPanel = new JPanel();
        JLabel cardLabel = new JLabel();
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
}
