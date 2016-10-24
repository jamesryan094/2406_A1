package gui;

import game.Game;

/** Created by james on 22/10/2016. */
public class UpdateLabels {

  public static void updateLastPlayedCardGUI(MineralST_GUI gui, Game newGame) {
    gui.currentPlayerLabel.setText("Card in Play: " + newGame.getLastPlayedCard().getTitle());
    gui.lastPlayerLabel.setText("Last Card Played By: " + newGame.getLastPlayer().getName());
    gui.currentCategoryLabel.setText(
        "Current Trump Category: " + newGame.getCurrentTrumpCategory());
    gui.currentValueLabel.setText(
        "Current Trump Value: "
            + newGame
                .getLastPlayedCard()
                .getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
  }

  public static void updateNextPlayerLabel(Game newGame, MineralST_GUI gui) {
    gui.nextPlayerLabel.setText("Next Player: " + newGame.getNextPlayer().getName());
  }

  public static void resetLabels(MineralST_GUI gui) {
    //        gui.currentPlayerLabel.setText("Card in Play: " + newGame.getLastPlayedCard().getTitle());
    gui.lastPlayerLabel.setText("Last Card Played By: ");
    gui.currentCategoryLabel.setText("Current Trump Category: ");
//    gui.currentValueLabel.setText("Current Trump Value: ");
  }

//  public static void setPlayerPassed(MineralST_GUI gui, Game newGame) {
//    String playerName = newGame.getCurrentPlayer().getName();
//    for (Component component : gui.players.getComponents()) {
//        if (component.getName().equals(playerName)){
//
//        }
//    }
//  }
}
