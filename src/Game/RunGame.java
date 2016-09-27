package Game;
//Todo: Ensure ALL input is collected in THIS file.
import Cards.Card;
import Cards.MineralCard;
import Trumps.TrumpCategory;

import java.util.ArrayList;
import java.util.Scanner;

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
//                System.out.println("Game Prepared");
                newGame.incrementCurrentPlayer();

                while (!newGame.isWon()){
                    int turnChoice;

                    if (!newGame.isHumanUp()){
                        if(!newGame.cardHasBeenPlayed()){
                            newGame.playFirstTurn();
                            System.out.println(newGame.getCurrentPlayer().getName() + " Played: " + newGame.getLastPlayedCard().getTitle());

                            System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
                            System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));

                            newGame.incrementCurrentPlayer();
                        } else {

//                            newGame.playFirstTurn();
//                            System.out.println(newGame.getCurrentPlayer().getName() + " Played: " + newGame.getLastPlayedCard().getTitle());
//                            System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
//                            System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));



                            System.out.println("Would usually play card if could");
                            System.out.println(newGame.getCurrentPlayer().getName() + " chooses to pass");
                            newGame.incrementCurrentPlayer();
                        }
                    }

                    while (newGame.isHumanUp()){
                        turnChoice = getValidTurnChoice();
                        switch (turnChoice){
                            case 0:
                                System.out.println("Display Hand");
                                displayHandMenu(newGame.getCurrentPlayer().getHand(), turnChoice, newGame);

                                break;
                            case 1:
                                System.out.println("play card");
//                                playcard()
                                displayHandMenu(newGame.getCurrentPlayer().getHand(), turnChoice, newGame);
                                System.out.println("Cool");
                                if(newGame.getHumanPlayedCard()) {
                                    System.out.println(newGame.getCurrentPlayer().getName() + " Played: " + newGame.getLastPlayedCard().getTitle());
                                    System.out.println("Current Trump Category: " + newGame.getCurrentTrumpCategory());
                                    System.out.println("Current Trump Value: " + newGame.getLastPlayedCard().getCurrentTrumpValueAsString(newGame.getCurrentTrumpCategory()));

                                    newGame.incrementCurrentPlayer();
                                    newGame.resetHumanPlayedCard();
                                }
                                break;

                            case 2:
                                System.out.println("pass");

                                newGame.incrementCurrentPlayer();
                                break;
                        }
                    }
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
        newGame.assignDealer();

        newGame.initialDeal();

        return newGame;
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
//            Card cardChoice = newGame.getCurrentPlayer().playCard()
            if(menuChoice== 0) {
                cardChoice.printAttributes();
            }
//            Else User wants to play card.
            else {
                if (cardChoice.getCardType().equals("trump")) {
                    if(cardChoice.isGeologist()){
                        String trumpChoice = getTrumpCategoryFromUser();
                        newGame.playFirstTurn(cardIndex, trumpChoice);
                    }
                    else{
                        newGame.playFirstTurn(cardIndex);
                    }
                }
                else{
                    System.out.println("display hand menu > play");
                    if (!newGame.cardHasBeenPlayed()) {
                        String trumpChoice = getTrumpCategoryFromUser();
                        newGame.playFirstTurn(cardIndex, trumpChoice);
                        }
                    else {
//                        if canplayon()
                        System.out.println("card played");

                        newGame.setHumanPlayedCard();
                        newGame.setLastPlayedCard(cardChoice);
                        userHand.remove(cardChoice);
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
