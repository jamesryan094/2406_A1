package Game;
//Todo: Ensure ALL input is collected in THIS file.
import Cards.Card;

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
    static final String TURN_MENU_MESSAGE = "(0)View Hand\n(1)Play Card\n(2)Pass";

    public static void main(String[] args) {
        System.out.print(MENU_MESSAGE);
        String menuChoice = getValidMenuChoice();
        while (!menuChoice.equals("Q")) {
            if (menuChoice.equals("P")) {
                Game newGame = prepareNewGame();
                System.out.println("Ready to Start! Have Fun!\n");
                while (!newGame.isWon()) {
                    if (newGame.checkWinner()){
                        newGame.updateWinners();
                    }
                    if (newGame.getNumPasses() == (newGame.getNumPlayers()-1)-newGame.getWinners().size()) {
//                        if everyone passes: reset round, set player to round winner.
                        newGame.resetRound();
                        System.out.println("\nRound Won By: " + newGame.getCurrentPlayer().getName() + "! Good Job!\nRound Reset\n");
                    }
                    int turnChoice;
//                    If the current player has not passed && if the current player is not in the winner list(is still playing)
                    if(!newGame.getCurrentPlayer().getHasPassed()&&!newGame.getWinners().contains(newGame.getCurrentPlayer())){


                        System.out.println("\nPress Enter to Continue >>>");
                        keys.nextLine();

                        System.out.println("Current Player: " + newGame.getCurrentPlayer().getName() + "\n--------------------");


                        if (!newGame.isHumanUp()) {
                            if (!newGame.cardHasBeenPlayed()) {
                                newGame.playFirstTurn();
                                System.out.println(newGame.getCurrentPlayer().getName() + " Played: " + newGame.getLastPlayedCard().getTitle());

                                System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
                                System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));

                                newGame.incrementCurrentPlayer();
                            } else {
                                if (newGame.getCurrentPlayer().hasPlayableCards(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())) {
                                    newGame.playTurn();
                                    System.out.println(newGame.getCurrentPlayer().getName() + " Played: " + newGame.getLastPlayedCard().getTitle());
                                    System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
                                    System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));
                                } else {
                                    System.out.println(newGame.getCurrentPlayer().getName() + " choose to Pass");
                                    newGame.passTurn();
                                    newGame.incrementNumPasses();
                                }
                                newGame.incrementCurrentPlayer();
                            }
                        } else {
                            while (newGame.isHumanUp()) {
                                turnChoice = getValidTurnChoice();
                                switch (turnChoice) {
                                    case 0:
                                        System.out.println("Display Hand: ");
                                        displayHandMenu(newGame.getCurrentPlayer().getHand(), turnChoice, newGame);

                                        break;
                                    case 1:
                                        System.out.println("Play Card");
                                        displayHandMenu(newGame.getCurrentPlayer().getHand(), turnChoice, newGame);
//                                        System.out.println("Cool");
                                        if (newGame.getHumanPlayedCard()) {
                                            System.out.println(newGame.getCurrentPlayer().getName() + " Played: " + newGame.getLastPlayedCard().getTitle());
                                            System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
                                            System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));

                                            newGame.incrementCurrentPlayer();
                                            newGame.resetHumanPlayedCard();
                                        }
                                        break;

                                    case 2:
                                        System.out.println(newGame.getCurrentPlayer().getName() + " choose to Pass");
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
//                System.out.println("End of Loop");
//                    if(newGame.getWinners().contains(newGame.getCurrentPlayer())){
//                        System.out.println(newGame.getCurrentPlayer().getName() + " has already won! They're sitting out");
//                        newGame.incrementCurrentPlayer();
//                    }
                }
            }
            System.out.print(MENU_MESSAGE);
            menuChoice = getValidMenuChoice();
        }
        System.out.println("Thank you for playing :V");
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
        while (turnMenuChoice != 0 && turnMenuChoice!= 1 && turnMenuChoice != 2){
            System.out.println("invalid choice, please try again");
            displayTurnMenu();
            turnMenuChoice = keys.nextInt();
            keys.nextLine();
        }
        return turnMenuChoice;
    }
    public static void displayHandMenu(ArrayList<Card> userHand, int menuChoice, Game newGame){
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
                if (cardChoice.getCardType().equals("trump")) {
                    if(newGame.cardHasBeenPlayed()) {
                        if (cardChoice.isGeologist()) {
                            String trumpChoice = getTrumpCategoryFromUser();
                            newGame.playFirstTurn(cardIndex, trumpChoice);
                        } else {
                            newGame.playFirstTurn(cardIndex);
                        }
                    }else{
                        System.out.println("\nYou must begin a round with a Mineral Card! Please try again.");
                    }
                }
                else{
                    if (!newGame.cardHasBeenPlayed()) {
                        String trumpChoice = getTrumpCategoryFromUser();
                        newGame.playFirstTurn(cardIndex, trumpChoice);
                        }
                    else {
                        if (cardChoice.canPlayOn(newGame.getLastPlayedCard(), newGame.getCurrentTrumpCategory())){

                            newGame.setHumanPlayedCard();
                            newGame.setLastPlayedAttributes(cardChoice);
                            userHand.remove(cardChoice);
                        }else {
                            System.out.println("Can't play that card, sorry.");
                        }
                    }
                }
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
