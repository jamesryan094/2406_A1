package Game;

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

    public static void main(String[] args) {

        System.out.print(MENU_MESSAGE);
        String menuChoice = getValidMenuChoice();
        while (!menuChoice.equals("Q")) {
            if (menuChoice.equals("P")) {
                Game newGame = prepareNewGame();
                newGame.setCurrentPlayer();
//                System.out.println("Current Player: " + newGame.getCurrentPlayer().getName());
                while (!newGame.isWon()){
                    Round.playRound(newGame);
//                    newGame.incrementRoundNum();
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
}
