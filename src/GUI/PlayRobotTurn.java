package GUI;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;

/**
 * Created by james on 20/10/2016.
 */
public class PlayRobotTurn {
    public static Game newGame;
    public static void playTurn(MineralST_GUI gui){
        newGame = Game.currentGame;
        if (newGame.isNewRound() || newGame.isFirstTurn()) {
            System.out.println("Is New Round");
            playFirstTurn(newGame, gui);
        }
        else{
            if (newGame.getCurrentPlayer().hasPlayableCards(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())){
                System.out.println("Not a new round");
                playNotFirstTurn(gui);
            }
        }
        updateLastPlayedCard(newGame, gui);
    }

    private static void updateLastPlayedCard(Game newGame, MineralST_GUI gui) {
        JPanel cardPanel = new JPanel();
        JLabel cardLabel = new JLabel();
        ImageIcon cardIcon = new ImageIcon("src/GUI/images/cards/" + newGame.getLastPlayedCard().getFileName());
        cardLabel.setIcon(cardIcon);
        cardPanel.add(cardLabel);
//        gui.lastPlayedCard.remove(0);
        gui.lastPlayedCard.removeAll();
        gui.lastPlayedCard.add(cardPanel);
    }

    private static void playNotFirstTurn(MineralST_GUI gui) {
        Game newGame = Game.currentGame;
        Player currentPlayer = newGame.getCurrentPlayer();
        if (currentPlayer.hasCombo()) {
            newGame.playCombo();
        } else {

            boolean haveCard = false;
            Card cardChoice = null;
            for (int i = 0; i < currentPlayer.getHand().size(); ++i) {
                if (currentPlayer.getHand().get(i).canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())
                        && !haveCard) {
                    cardChoice = currentPlayer.playCard(i);
                    haveCard = true;
                }
            }
            if (cardChoice != null && cardChoice.getCardType().equals("trump")) {
                String trumpChoice;
                if (cardChoice.isGeologist()) {
                    trumpChoice = currentPlayer.getTrumpCategoryChoice();
                } else {
                    trumpChoice = newGame.getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
                }
                newGame.setCurrentTrumpCategory(trumpChoice);
                newGame.resetRoundTrump();
            }
            newGame.setLastPlayedAttributes(cardChoice);
            gui.currentCategoryLabel.setText("Current Trump Category: " + newGame.getCurrentTrumpCategory());
            gui.currentValueLabel.setText("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
        }
    }

    private static void playFirstTurn(Game newGame, MineralST_GUI gui) {
        String trumpChoice;
        Card cardChoice;
        newGame.resetComboPlayed();
        //first turn of game
        if (newGame.isFirstTurn) {
            cardChoice = newGame.getCurrentPlayer().playAnyMineralCard();
            trumpChoice = newGame.getCurrentPlayer().getTrumpCategoryChoice();
            newGame.setFirstTurn(false);
        }
        //first turn of ROUND
        else {
            cardChoice = newGame.getCurrentPlayer().playAnyCard();
            if (cardChoice.getCardType().equals("play") || cardChoice.isGeologist()) {
                trumpChoice = newGame.getCurrentPlayer().getTrumpCategoryChoice();
            } else {
                trumpChoice = newGame.getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
            }
        }
        newGame.setCurrentTrumpCategory(trumpChoice);
        newGame.setLastPlayedAttributes(cardChoice);
        System.out.println(newGame.getCurrentTrumpCategory());
        System.out.println(newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
        gui.currentCategoryLabel.setText("Current Trump Category: " + newGame.getCurrentTrumpCategory());
        gui.currentValueLabel.setText("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
    }
}
