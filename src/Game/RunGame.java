package Game;
//Todo: Ensure ALL input is collected in THIS file.
import Cards.Card;

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
                newGame.setCurrentPlayer();

                while (!newGame.isWon()){
                    int turnChoice;
//                    newGame.setCurrentPlayer();

                    if (!newGame.isHumanUp()){
//                        newGame.playRobotTurn();
                        System.out.println(newGame.getCurrentPlayer().getName() + " chooses to pass");
                        newGame.setCurrentPlayer();
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
                                if(newGame.getHumanPlayedCard()) {
                                    newGame.setCurrentPlayer();
                                }
                                break;

                            case 2:
                                System.out.println("pass");

                                newGame.setCurrentPlayer();
                                break;
                        }

                    }
//                    Round.playRound(newGame);
                }







//                If the current player is human
//                if (newGame.getCurrentPlayer().getName().equals(newGame.getPlayers()[0].getName())){
//
//                }
//                if (user is up){
//                    get user card choice
//                            playturn (card choice)
//                }
//                else {
//                    playturn();
//                }

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
//        newGame.testAssignDealer();
//        System.out.println("Dealer is: " + newGame.getDealer().getName());
//        newGame.printParty();
        newGame.initialDeal();
//        System.out.println("Your hand has been dealt");

//        for(int i = 0; i < newGame.players.size();++i){
//            System.out.println(newGame.players.get(i).name);
//            for (Cards.Card card:newGame.players.get(i).hand){
//                System.out.println("New Cards.Card");
//                card.printAttributes();
//            }
//                }
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
            if(menuChoice== 0) {
                cardChoice.printAttributes();
            }
            else{
                System.out.println("display hand menu > play");
                newGame.cardHasBeenPlayed();
//            Game.playCard(cardChoice));
            }
        }
        else{

        }
    }


    public static void printCards(ArrayList<Card> userHand){
        for(int i=0; i < userHand.size(); ++i){
            System.out.println("("+ (i+1) + ") " + userHand.get(i).getTitle());
        }
        System.out.println("(0) Back");
        System.out.print("Enter Card Index >>> ");
    }

//        public static int getValidHandMenuChoice(){
//            displayHandMenu();
//
//        }
}
