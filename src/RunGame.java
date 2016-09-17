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
        String menuChoice;

        Deck deck = GenerateDeckFromPLIST.buildDeck();

        System.out.print(MENU_MESSAGE);
        menuChoice = getValidMenuChoice();
        while(!menuChoice.equals("Q")) {
            if (menuChoice.equals("P")) {
                int numPlayersChoice = Game.getValidNumPlayers();
                Game newGame = new Game(numPlayersChoice);
                newGame.assignDealer();
                newGame.printParty();
                deck.initialDeal(newGame);
                for(int i = 0; i < newGame.players.size();++i){
                    System.out.println(newGame.players.get(i).name);
                    for (Card card:newGame.players.get(i).hand){
                        System.out.println("New Card");
                        card.printAttributes();
                    }
                }
            }
            System.out.print(MENU_MESSAGE);
            menuChoice = getValidMenuChoice();
        }

        System.out.println("Thank you for playing :V");

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
