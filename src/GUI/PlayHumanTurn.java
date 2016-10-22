package GUI;

import cards.Card;
import game.Game;
import player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by james on 20/10/2016.
 */
public class PlayHumanTurn {
//    Game newGame;
//    MineralST_GUI gui;

    public static void enableUserButtons(MineralST_GUI gui) {
        gui.continueButton.setEnabled(false);
        gui.playCardButton.setEnabled(true);
        gui.playComboButton.setEnabled(true);
        gui.passButton.setEnabled(true);
    }

    public static void playTurn(MineralST_GUI gui) {
        Game newGame = Game.currentGame;
        Player currentPlayer = newGame.getCurrentPlayer();
        Component[] userCardComponents = gui.userCards.getComponents();
        Card cardChoice = null;
        for (Component component:userCardComponents){
            if(component.isVisible()){
                JPanel cardPanel = (JPanel) component;
                cardChoice = getCard(currentPlayer, cardPanel);
            }
        }

        boolean cardPlayed = true;
        //if first turn
            //if trump
                //PRINT - "NO"
            //else - mineral
                //getTrumpChoice()
                //playFirstTurn()
        if (newGame.isFirstTurn()) {
            if (cardChoice.getCardType().equals("trump")) {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "You can not start the game with a trump card!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
                newGame.playFirstTurn(cardChoice, trumpChoice);
            }
        }
        else if (newGame.isNewRound() || newGame.comboPlayed()) {
            if (cardChoice.getCardType().equals("trump")) {
                if (cardChoice.isGeologist()) {
                    String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
                    newGame.playFirstTurn(cardChoice, trumpChoice);
                    newGame.resetRoundTrump();
                } else {
                    newGame.playTrumpCardGUI(cardChoice);
                }
            } //CardType = mineral
            else {
                String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
                newGame.playFirstTurn(cardChoice, trumpChoice);
            }
        }
        else {
            if (cardChoice.canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())) {
                if (cardChoice.getCardType().equals("trump") && !cardChoice.isGeologist()) {
                    newGame.playTrumpCardGUI(cardChoice);
                } else if (cardChoice.isGeologist()) {
                    String trumpChoice = getTrumpCategoryFromUser(gui, newGame);
                    newGame.playFirstTurn(cardChoice, trumpChoice);
                    newGame.resetRoundTrump();
                }
                //chose mineral card that can play on last played
                newGame.playTurn(cardChoice);
                newGame.setHumanPlayedCard();
                //last played attributes set in game
//                newGame.setLastPlayedAttributes(cardChoice);
            } else {
                cardPlayed = false;
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "You can not play that card\nMake sure you pay attention to the current trump value and category.", "Hold It!", JOptionPane.ERROR_MESSAGE);
            }
        }
            if (cardPlayed){
                PrepareGame.generateHandIcons(gui, newGame);
                ContinuePressed.updateLastPlayedCard(newGame, gui);
                UpdateLabels.updateLastPlayedCardGUI(gui, newGame);
                disableUserButtons(gui);
            }




        //else - if cardChoice canPlayOn()
            //if cardChoice is TRUMP & NOT GEOLOGIST
                //playTrumpCard()
                //updateTrumpCategory()
            //else - if cardChoice ISGEOLOGIST()
                //getTrumpChoice()
                //playFirstTurn()
                //resetRoundTrump()
            //newGame.setHumanPlayedCard();
            //newGame.setLastPlayedAttributes(cardChoice);
            //userHand.remove(cardChoice);
        //else - CARD CANT PLAY
            //JOPTIONPANE.SHOWMESSAGEDIALOGUE("CANT PLAY THAT CARD")




        }



    private static String getTrumpCategoryFromUser(MineralST_GUI gui, Game newGame) {
//        final JPanel panel = new JPanel();
//        JOptionPane.showMessageDialog(panel, "You must enter a username to start a game.", JOptionPane.OPTIONS_PROPERTY, JOptionPane.ERROR_MESSAGE);
        String[] trumps = {"Cleavage", " Crustal Abundance", " Economic Value", "Hardness", "Specific Gravity"};
        String trumpChoice = (String) JOptionPane.showInputDialog(null, "Select a Trump Category!",
                "Select Category", JOptionPane.QUESTION_MESSAGE, null
                , trumps// Array of choices
                , trumps[0]); // Initial choice
        return trumpChoice;
    }

    private static Card getCard(Player currentPlayer, JPanel cardChoice) {
        for(Card card:currentPlayer.getHand()){
            if(card.getTitle().equals(cardChoice.getName())){
                int cardIndex = currentPlayer.getHand().indexOf(card);
                return currentPlayer.getHand().get(cardIndex);
            }
        }
        return null;
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
