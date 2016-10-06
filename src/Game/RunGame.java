package Game;

import Cards.Card;
import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Acts as conduit between player and system by taking input and print output, controls flow of game.
 * Instantiates Game object then manipulates attributes based on actions of human and nonHuman players.
 * Establishes rules of the game
 * Error checks input from user and provides output for user.
 *
 * Created by james on 6/09/2016.
 */
public class RunGame {

    private static String MENU_MESSAGE = "MINERAL SUPER TRUMPS" +
            "\n--------------------" +
            "\n(P)lay" +
            "\n(I)nstructions" +
            "\n(T)rump Hierarchies" +
            "\n(Q)uit" +
            "\n>>>";
    private static Scanner keys = new Scanner(System.in);
    private static final String TURN_MENU_MESSAGE = "(0)Inspect Hand\n(1)Play Card\n(2)Combo\n(3)Pass";

    public static void main(String[] args) {
        System.out.print(MENU_MESSAGE);
        String menuChoice = getValidMenuChoice();
        while (!menuChoice.equals("Q")) {
            switch (menuChoice) {
                case "I":
                    printInstructions();
                    break;
                case "T":
                    printTrumpHierarchies();
                    break;
                case "P":
                    Game newGame = prepareNewGame();
                    System.out.println("Ready to Start! Have Fun!\n");
                    while (!newGame.isWon()) {
                        Player currentPlayer = newGame.getCurrentPlayer();
                        //if they haven't won
                        if (!newGame.getWinners().contains(currentPlayer)) {
                            int turnChoice;
//                    If the current player has not passed
                            if (!currentPlayer.getHasPassed()) {
                                boolean isNewRound = newGame.isNewRound();
                                if (isNewRound) {
                                    //                        if everyone passes: reset round, set player to round winner.
                                    newGame.resetRound();
                                    System.out.println("\nRound Won By: " + currentPlayer.getName() + "! Good Job!\n\nRound Reset!\n");
                                }
                                System.out.println("\nPress Enter to Continue >>>");
                                keys.nextLine();
                                System.out.println("Current Player: " + currentPlayer.getName() + "\n--------------------");
                                if (!newGame.isHumanUp()) {
//                                Robot is up:
                                    if (isNewRound || newGame.isFirstTurn()) {
                                        newGame.playFirstTurn();
                                        displayTurnResults(newGame);
                                    } else {
                                        if (currentPlayer.hasPlayableCards(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())) {
                                            newGame.playTurn();
                                            displayTurnResults(newGame);
                                        } else {
                                            System.out.println(newGame.getCurrentPlayer().getName() + " chose to Pass");
                                            newGame.passTurn();
                                            newGame.incrementNumPasses();
                                        }
                                    }
                                    boolean comboPlayed = newGame.comboPlayed();
                                    if (newGame.checkWinner()) {
                                        newGame.updateWinners();
                                        System.out.println("Congratulations " + newGame.getCurrentPlayer().getName() + "! You have been added to the winner list!");
                                    }
                                    if (!comboPlayed) {
                                        newGame.incrementCurrentPlayer();
                                    } else {
                                        System.out.println(currentPlayer.getName() + " played the COMBO! They win the round and get to go again.");
                                        newGame.playFirstTurn();
                                        newGame.resetComboPlayed();
                                    }
                                } else {
                                    while (newGame.isHumanUp()) {
                                        boolean comboPlayed = newGame.comboPlayed();
//                                    turnChoice = getValidTurnChoice();
                                        turnChoice = getNumInRange(0, 3, TURN_MENU_MESSAGE);
                                        switch (turnChoice) {
                                            case 0:
                                                System.out.println("Inspect Hand: ");
                                                displayHandMenu(currentPlayer.getHand(), turnChoice, newGame, isNewRound, comboPlayed);
                                                break;
                                            case 1:
                                                System.out.println("Play Card: ");
                                                displayHandMenu(currentPlayer.getHand(), turnChoice, newGame, isNewRound, comboPlayed);
                                                if (newGame.getHumanPlayedCard()) {
                                                    displayTurnResults(newGame);
                                                    newGame.resetHumanPlayedCard();
                                                    if (newGame.checkWinner()) {
                                                        newGame.updateWinners();
                                                        System.out.println("Congratulations " + newGame.getCurrentPlayer().getName() + "! You have been added to the winner list!");
                                                    }
                                                    newGame.incrementCurrentPlayer();
                                                }
                                                break;
                                            case 2:
                                                if (currentPlayer.hasCombo()) {
                                                    if (newGame.isFirstTurn()) {
                                                        System.out.println("Can't play combo on first turn.");
                                                    } else {
                                                        newGame.playCombo();
                                                        System.out.println(currentPlayer.getName() + " played the COMBO! They win the round and get to go again.");
                                                        newGame.resetComboPlayed();
                                                        if (newGame.checkWinner()) {
                                                            newGame.updateWinners();
                                                            System.out.println("Congratulations " + newGame.getCurrentPlayer().getName() + "! You have been added to the winner list!");
                                                            newGame.incrementCurrentPlayer();
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("You do not have the combo.");
                                                }
                                                break;
                                            case 3:
                                                System.out.println(newGame.getCurrentPlayer().getName() + " chose to Pass");
                                                newGame.passTurn();
                                                newGame.incrementNumPasses();
                                                newGame.incrementCurrentPlayer();
                                                break;
                                        }
                                    }
                                }
                            } else {
                                System.out.println('\n' + currentPlayer.getName() + " is out for the round.");
                                newGame.incrementCurrentPlayer();
                            }
                            newGame.setFirstTurn(false);
                        } else {
                            System.out.println('\n' + currentPlayer.getName() + " already won!");
                            newGame.incrementCurrentPlayer();
                        }
                    }
                    System.out.println("Game completed! Well done everyone!\n");
                    break;
            }
            System.out.print(MENU_MESSAGE);
            menuChoice = getValidMenuChoice();
        }
        System.out.println("Thank you for playing :V");
    }

    private static void printTrumpHierarchies() {
        System.out.println(Game.TRUMP_HIERARCHIES);
    }

    private static void printInstructions() {
        System.out.println(Game.INSTRUCTIONS);
    }

    /**
     * Prints the currently selected trump category and the corresponding trump value
     * of the most recently played card.
     * @param newGame the game object that stores the last played card.
     */
    private static void displayTurnResults(Game newGame) {
        Player currentPlayer = newGame.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + " Played: " + newGame.getLastPlayedCard().getTitle());

        System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
        System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));

        if (newGame.getLastPlayedCard().getCardType().equals("trump")){
            System.out.println('\n' + currentPlayer.getName() + " played a trump!\n" +
            "Everybody is back in the round!\n");
        }
    }

    /**
     * Instantiates a game object based on information taken as input and prepares the game for
     * playing by setting setting a dealer, dealing cards to each player, and setting a current, first player.
     * @return the prepared game object
     */
    private static Game prepareNewGame() {
        String numPlayerPrompt = "How many players (3-5)?";
        int numPlayersChoice = getNumInRange(Game.MIN_PLAYERS, Game.MAX_PLAYERS, numPlayerPrompt);
        String userName = getValidUserName();
        Game newGame = new Game(numPlayersChoice, userName);
        System.out.println("\nRandomly assigning dealer...");
        newGame.assignDealer();
        System.out.println("Dealer: " + newGame.getDealer().getName());
        System.out.println("\n"+ newGame.getDealer().getName() + " is dealing the cards...");
        newGame.initialDeal();
        System.out.println("Cards dealt\n");
        newGame.incrementCurrentPlayer();
        printUserCards(newGame);
        return newGame;
    }

    /**
     * Prompts user for username. Username is valid if input is not blank or not exclusively whitespace.
     * @return a valid, non blank player username.
     */
    private static String getValidUserName() {
        Scanner keys = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String userName = keys.nextLine();
        String strippedName = userName.replaceAll("\\s+", "");
        while (strippedName.isEmpty()){
            System.out.println("Invalid username: Username can not be blank.");
            System.out.println("Enter Username: ");
            userName = keys.nextLine();
            strippedName = userName.replaceAll("\\s+", "");
        }
        return userName;
    }

    private static void printUserCards(Game newGame) {
        System.out.println("Your hand contains: ");
        ArrayList<Card> hand = newGame.getPlayers()[0].getHand();
        for (Card aHand : hand) {
            System.out.println(aHand.getTitle());
        }
        System.out.println("");
    }

    /**
     * Prompts user for Menu Choice. Menu Choice is valid if input is one of 4 predetermined characters.
     * Will constantly reprompt the user until a valid character is entered (not case sensitive).
     * @return a valid menu choice.
     */
    private static String getValidMenuChoice() {
        Boolean isValid = false;
        Scanner keys = new Scanner(System.in);

        String userInput = keys.nextLine().toUpperCase();

        while (!isValid){
            if ((userInput.equals("Q")) || (userInput.equals("P")) || (userInput.equals("I")) || (userInput.equals("T"))){
                isValid = true;
            }
            else if (userInput.length()>1){
                System.out.println("Too long, enter one character");
                System.out.print(MENU_MESSAGE);
                userInput = keys.nextLine().toUpperCase();
            }
            else if(userInput.length() == 0){
                System.out.println("Input can not be blank, choose again");
                System.out.print(MENU_MESSAGE);
                userInput = keys.nextLine().toUpperCase();
            }
            else{
                System.out.println("Not valid menu choice.\nTry again.");
                System.out.print(MENU_MESSAGE);
                userInput = keys.nextLine().toUpperCase();
            }
        }

        return userInput;

    }

    /**
     * Checks input when user is to select a whole integer value in a range.
     * Returns an integer representing a menu choice.
     * @param min the lower boundary of the acceptable range of choices.
     * @param max the upper boundary of the acceptable range of choices.
     * @param menuPrompt argument to display the appropriate message when prompting/reprompti
     * @return a valid menu choice as int.
     */
    private static int getNumInRange(int min, int max, String menuPrompt){
        Scanner keys = new Scanner(System.in);
        String potentialNum;

//        System.out.println("How many players (3-5)?");
        System.out.println(menuPrompt);
        potentialNum = keys.nextLine();

        int num = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                num = Integer.parseInt(potentialNum);

                if ((num < min) || (num > max)){
                    System.out.println("Invalid choice, try again");
                    System.out.println(menuPrompt);
                    potentialNum = keys.nextLine();
                } else{
                    isValid = true;
                }
            } catch (NumberFormatException numE) {
                System.out.println("Please enter a valid number");
                System.out.println(menuPrompt);
                potentialNum = keys.nextLine();
            }

        }
        return num;
    }

    /**
     * Provides main functionality for user when playing their turn.
     * A list of options are displayed to user. From here user can choose to
     * inspect card details, play a card, play the combo or pass.
     * @param userHand the human players current hand of cards as an ArrayList cast to Card objects.
     * @param menuChoice the intention of the user. HandMenu will decide how to respond to a card choice
     *                   based on the previous menu choice (inspect or play) @see turnMenu
     * @param newGame the game object.
     * @param isNewRound determines weather human player is to pick a trump category after playing a mineral card or not
     * @param comboPlayed allows a human to play another card after playing a combo.
     */
    private static void displayHandMenu(ArrayList<Card> userHand, int menuChoice, Game newGame, boolean isNewRound, boolean comboPlayed){
        int handMenuChoice;


        printCards(userHand);
//        handMenuChoice = keys.nextInt();
//        keys.nextLine();
        String handMenuPrompt = "Enter Card Index >>> ";

        handMenuChoice = getNumInRange(0, userHand.size(), handMenuPrompt);

        if (handMenuChoice != 0){
            Card cardChoice = userHand.get(handMenuChoice -1);
            int cardIndex = handMenuChoice -1;
            if(menuChoice== 0) {
                cardChoice.printAttributes();
            }
//            Else User wants to play card.
            else {
                if (newGame.isFirstTurn()) {
                    if (cardChoice.getCardType().equals("trump")) {
                        System.out.println("Can't play Supertrump on first turn.");
                    }
                    //first turn and is a mineral card
                    else {
                        String trumpChoice = getTrumpCategoryFromUser();
                        newGame.playFirstTurn(cardIndex, trumpChoice);
                    }
                }
                else if (isNewRound || comboPlayed){
                    //new round and user chose trump
                    if (cardChoice.getCardType().equals("trump")) {
                        if (cardChoice.isGeologist()) {
                            String trumpChoice = getTrumpCategoryFromUser();
                            newGame.playFirstTurn(cardIndex, trumpChoice);
                            newGame.resetRoundTrump();
                        } else {
                            newGame.playTrumpCard(cardIndex);
                        }
                    }
                    //new round and user chose a mineral card
                    else {
                        String trumpChoice = getTrumpCategoryFromUser();
                        newGame.playFirstTurn(cardIndex, trumpChoice);
                    }
                }
                //not new round and not first turn
                else {
                    if (cardChoice.canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())){
                        if (cardChoice.getCardType().equals("trump") && !cardChoice.isGeologist()){
//                            String trumpChoice = newGame.getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
//                            newGame.setCurrentTrumpCategory(trumpChoice);
                            newGame.playTrumpCard(cardIndex);
                        }
                        else if (cardChoice.isGeologist()){
                            String trumpChoice = getTrumpCategoryFromUser();
//                            newGame.setCurrentTrumpCategory(trumpChoice);
                            newGame.playFirstTurn(cardIndex, trumpChoice);
                            newGame.resetRoundTrump();
                        }
                        newGame.setHumanPlayedCard();
                        newGame.setLastPlayedAttributes(cardChoice);
                        userHand.remove(cardChoice);
                    }else {
//                            Todo: maybe give more information here. eg, X is not preferable to Y for [TRUMP] (X.TRUMP < Y.TRUMP).
                        System.out.println("Can't play that card, sorry.");
                    }
                }
//                //Not first turn and playing trump card
//                if (!newGame.isFirstTurn() && cardChoice.getCardType().equals("trump")) {
//                    if (cardChoice.isGeologist()) {
//                        String trumpChoice = getTrumpCategoryFromUser();
//                        newGame.playFirstTurn(cardIndex, trumpChoice);
//                    } else {
//                        newGame.playTrumpCard(cardIndex);
//                    }
//                }
//                else{
//                    //first turn
//                    if (newGame.isNewRound()) {
//                        String trumpChoice = getTrumpCategoryFromUser();
//                        newGame.playFirstTurn(cardIndex, trumpChoice);
//                        }
//                        //not first turn and not supertrump
//                    else {
//                        if (cardChoice.canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())){
//
//                            newGame.setHumanPlayedCard();
//                            newGame.setLastPlayedAttributes(cardChoice);
//                            userHand.remove(cardChoice);
//                        }else {
////                            Todo: maybe give more information here. eg, X is not preferable to Y for [TRUMP] (X.TRUMP < Y.TRUMP).
//                            System.out.println("Can't play that card, sorry.");
//                        }
//                    }
//                }
            }
        }
    }

    /**
     * Prompts user for trump category choice as int.
     * Validates input with getNumInRange() and returns a valid trump choice.
     * @return a string representing the trump value selected to play.
     */
    private static String getTrumpCategoryFromUser() {
        int trumpChoice;
        String TRUMP_CATEGORY_MESSAGE = "Select a Trump Category: \n(1) Cleavage\n(2) Crustal Abundance\n(3) Economic Value" +
                "\n(4) Hardness\n(5) Specific Gravity";
        String[] trumpStrings = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};
        trumpChoice = getNumInRange(1, 5, TRUMP_CATEGORY_MESSAGE);
        return trumpStrings[trumpChoice-1];
    }

    private static void printCards(ArrayList<Card> userHand){
        for(int i=0; i < userHand.size(); ++i){
            System.out.println("("+ (i+1) + ") " + userHand.get(i).getTitle());
        }
        System.out.println("(0) Back");
    }
    }
