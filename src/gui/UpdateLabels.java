package gui;

import game.Game;

/**
 * Updates JLabels on the user's control panel where appropriate
 * Created by james on 22/10/2016. */
class UpdateLabels {

  /**
   * gets the last played card from the current game object and creates appropriate icon to
   * display to user in "last played card" section
   * @param gui The custom Mineral Supertrumps gui object
   * @param newGame The current Game object
   */
  static void updateLastPlayedCardGUI(MineralST_GUI gui, Game newGame) {
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

  static void updateNextPlayerLabel(Game newGame, MineralST_GUI gui) {
    gui.nextPlayerLabel.setText("Next Player: " + newGame.getNextPlayer().getName());
  }

  /**
   * Changes label "Dealer: " to "Last Card Played By: " after pressing continue for the first time
   * The user does not need to know who the dealer is once the first turn has been played.
   * @param gui The custom Mineral Supertrumps gui object
   */
  static void resetLabels(MineralST_GUI gui) {
    gui.lastPlayerLabel.setText("Last Card Played By: ");
    gui.currentCategoryLabel.setText("Current Trump Category: ");
  }
}
