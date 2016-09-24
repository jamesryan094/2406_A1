package Game;
import Game.*;

import java.util.Scanner;

/**
 * Created by james on 22/09/2016.
 */

public class PlayTurn {

    static void turnMenu(Game newGame){
        final String MENUMESSAGE = "(0)View Hand\n(1)Play Card\n(2)Pass";
        int menuChoice;
        System.out.println(MENUMESSAGE);
        Scanner keys = new Scanner(System.in);
        menuChoice = keys.nextInt();
        keys.nextLine();
        while(menuChoice != 2){
            if (menuChoice==0){
                newGame.getCurrentPlayer().displayHand();

                System.out.println(MENUMESSAGE);
                menuChoice = keys.nextInt();
                keys.nextLine();
            }
            else if(menuChoice == 1){
                System.out.println("Play Card, Under Construction");

                Round.setLastCardPlayedBy(newGame.getCurrentPlayer());
//                System.out.println("last card played by: " + Round.getLastCardPlayedBy().getName());

                System.out.println(MENUMESSAGE);
                menuChoice = keys.nextInt();
                keys.nextLine();
            }
            else{
                System.out.println("Oh no!!");

                System.out.println(MENUMESSAGE);
                menuChoice = keys.nextInt();
                keys.nextLine();
            }
        }
        System.out.println("You choose to pass this turn");
    }
}
