package Game;
//Todo: Ensure ALL input is collected in THIS file.
import Cards.Card;
import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;

//Todo: Game loops if player before human passes. refactor code so increment player is done elsewhere (in play/pass)
/**
 * Created by james on 6/09/2016.
 *
 */
public class RunGame {
    static String MENU_MESSAGE = "MINERAL SUPER TRUMPS" +
            "\n--------------------" +
            "\n(P)lay" +
            "\n(Q)uit" +
            "\n>>>";

    static Scanner keys = new Scanner(System.in);
    static final String TURN_MENU_MESSAGE = "(0)Inspect Hand\n(1)Play Card\n(2)Combo\n(3)Pass";

    public static void main(String[] args) {
        System.out.print(MENU_MESSAGE);
        String menuChoice = getValidMenuChoice();
        while (!menuChoice.equals("Q")) {
            if (menuChoice.equals("P")) {
                Game newGame = prepareNewGame();
                System.out.println("Ready to Start! Have Fun!\n");
                while (!newGame.isWon()) {
                    Player currentPlayer = newGame.getCurrentPlayer();
                    //if they haven't won
                    if (!newGame.getWinners().contains(currentPlayer)) {
                        int turnChoice;
//                    If the current player has not passed
                        if(!currentPlayer.getHasPassed()){
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
                                if (newGame.checkWinner()){
                                    newGame.updateWinners();
                                    System.out.println("Congratulations " + newGame.getCurrentPlayer().getName() + "! You have been added to the winner list!");
                                }
                                if (!comboPlayed) {
                                    newGame.incrementCurrentPlayer();
                                }
                                else {
                                    System.out.println(currentPlayer.getName() + " IS A COMBOBREAKER!!! They go again.");
                                    newGame.playFirstTurn();
                                    newGame.resetComboPlayed();
                                }
                            }
                            else {
                                while (newGame.isHumanUp()) {
                                    boolean comboPlayed = newGame.comboPlayed();
                                    turnChoice = getValidTurnChoice();
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
                                                if (newGame.checkWinner()){
                                                    newGame.updateWinners();
                                                    System.out.println("Congratulations " + newGame.getCurrentPlayer().getName() + "! You have been added to the winner list!");
                                                }
                                                newGame.incrementCurrentPlayer();
                                            }
                                            break;
                                        case 2:
                                            if(currentPlayer.hasCombo()){
                                                if (newGame.isFirstTurn()){
                                                    System.out.println("Can't play combo on first turn.");
                                                }
                                                else {
                                                    newGame.playCombo();
                                                    System.out.println(currentPlayer.getName() + " IS A COMBOBREAKER!!! They go again.");
                                                    if (newGame.checkWinner()){
                                                        newGame.updateWinners();
                                                        System.out.println("Congratulations " + newGame.getCurrentPlayer().getName() + "! You have been added to the winner list!");
                                                        newGame.incrementCurrentPlayer();
                                                    }
                                                }
                                            }
                                            else {
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
                        }
                        else{
                            newGame.incrementCurrentPlayer();
                        }
                        newGame.setFirstTurn(false);
                    }
                    else {
                        System.out.println(currentPlayer.getName() + " already won!");
                        newGame.incrementCurrentPlayer();
                    }
                }
                System.out.println("IS OVEEERRRRRRR OH NOOOOOOOOOO");
            }
            System.out.print(MENU_MESSAGE);
            menuChoice = getValidMenuChoice();
        }
        System.out.println("Thank you for playing :V");
    }

    private static void displayTurnResults(Game newGame) {
        Player currentPlayer = newGame.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + " Played: " + newGame.getLastPlayedCard().getTitle());

        System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
        System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
    }


    static Game prepareNewGame() {
        int numPlayersChoice = getValidNumPlayers();
        Scanner keys = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String userName = keys.nextLine();
        Game newGame = new Game(numPlayersChoice, userName);
        System.out.println("\nRandomly assigning dealer...");
        newGame.assignDealer();
        System.out.println("\n"+ newGame.getDealer().getName() + " is dealing the cards...");
        newGame.initialDeal();
        System.out.println("Cards dealt\n");
        newGame.incrementCurrentPlayer();
        printUserCards(newGame);
        return newGame;
    }

    private static void printUserCards(Game newGame) {
        System.out.println("Your hand contains: ");
        ArrayList<Card> hand = newGame.getPlayers()[0].getHand();
        for (int i = 0; i < hand.size(); ++i){
            System.out.println(hand.get(i).getTitle());
        }
        System.out.println("");
    }


    public static int getValidNumPlayers(){
        Scanner keys = new Scanner(System.in);
        int num;

        System.out.println("How many players (3-5)?");
        num = keys.nextInt();
        keys.nextLine();

        while ((num != 3) && (num != 4) && (num != 5)){
            System.out.println("Invalid choice, try again");
            System.out.println("How many players (3-5)?");
            num = keys.nextInt();
            keys.nextLine();
        }
        return num;
    }

    private static String getValidMenuChoice() {
        Boolean isValid = false;
        Scanner keys = new Scanner(System.in);

        String userInput = keys.nextLine().toUpperCase();

        while (!isValid){
            if ((userInput.equals("Q")) || (userInput.equals("P"))){
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

    static void displayTurnMenu(){
        System.out.println(TURN_MENU_MESSAGE);
    }

    static int getValidTurnChoice(){
        int turnMenuChoice;
        Scanner keys = new Scanner(System.in);
        displayTurnMenu();
        turnMenuChoice = keys.nextInt();
        keys.nextLine();
        while (turnMenuChoice != 0 && turnMenuChoice!= 1 && turnMenuChoice != 2 && turnMenuChoice != 3){
            System.out.println("invalid choice, please try again");
            displayTurnMenu();
            turnMenuChoice = keys.nextInt();
            keys.nextLine();
        }
        return turnMenuChoice;
    }
    public static void displayHandMenu(ArrayList<Card> userHand, int menuChoice, Game newGame, boolean isNewRound, boolean comboPlayed){
        int handMenuChoice;
        Scanner keys = new Scanner(System.in);


        printCards(userHand);
        handMenuChoice = keys.nextInt();
        keys.nextLine();

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
                            String trumpChoice = newGame.getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
                            newGame.setCurrentTrumpCategory(trumpChoice);
                        }
                        else if (cardChoice.isGeologist()){
                            String trumpChoice = getTrumpCategoryFromUser();
                            newGame.setCurrentTrumpCategory(trumpChoice);
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

    private static String getTrumpCategoryFromUser() {
        Scanner keys = new Scanner(System.in);
        int trumpChoice;
        String TRUMP_CATEGORY_MESSAGE = "(1) Cleavage\n(2) Crustal Abundance\n(3) Economic Value" +
                "\n(4) Hardness\n(5) Specific Gravity";
        String[] trumpStrings = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};
        System.out.println(TRUMP_CATEGORY_MESSAGE);
        trumpChoice = keys.nextInt();
        keys.nextLine();
        return trumpStrings[trumpChoice-1];



    }



    public static void printCards(ArrayList<Card> userHand){
        for(int i=0; i < userHand.size(); ++i){
            System.out.println("("+ (i+1) + ") " + userHand.get(i).getTitle());
        }
        System.out.println("(0) Back");
        System.out.print("Enter Card Index >>> ");
    }




}
