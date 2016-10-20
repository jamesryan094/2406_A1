package GUI;

import game.Game;

/**
 * Created by james on 20/10/2016.
 */
public class PlayHumanTurn {

    public static void enableUserButtons(MineralST_GUI gui) {
        gui.continueButton.setEnabled(false);
        gui.playCardButton.setEnabled(true);
        gui.playComboButton.setEnabled(true);
        gui.passButton.setEnabled(true);
    }

    public static void playTurn(MineralST_GUI gui) {

    }

    public static void passTurn(MineralST_GUI gui){
        Game newGame = Game.currentGame;
        System.out.println(newGame.getCurrentPlayer().getName() + " chose to Pass");
        newGame.passTurn();
        newGame.incrementNumPasses();
        disableUserButtons(gui);

//        newGame.incrementCurrentPlayer();
    }

    private static void disableUserButtons(MineralST_GUI gui) {
        gui.continueButton.setEnabled(true);
        gui.playCardButton.setEnabled(false);
        gui.playComboButton.setEnabled(false);
        gui.passButton.setEnabled(false);
    }


}
