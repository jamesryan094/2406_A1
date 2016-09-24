package Game;

import Cards.*;
import Deck.*;
import Game.*;
import Player.*;
import Trumps.*;

/**
 * Created by james on 22/09/2016.
 */

public class Round {
    static Player lastCardPlayedBy;

    static void playRound(Game newGame){
//        System.out.println("Round " + newGame.getRoundNum() + "\n----------");

//        do{
            if (newGame.getCurrentPlayer().isHuman()) {
                PlayTurn.turnMenu(newGame);
                newGame.setCurrentPlayer();
            }
            else {
                //                        playTurn.robotTurn();
                System.out.println("Player Up: " + newGame.getCurrentPlayer().getName());
                System.out.println(newGame.getCurrentPlayer().getName() + " passes!");
                newGame.setCurrentPlayer();
                //                        System.out.println("Oh no");
            }
//        }
//        while (!lastCardPlayedBy.equals(newGame.getCurrentPlayer()));

//        System.out.println("Last card Played by same person");
        newGame.incrementRoundNum();
    }

    public static void setLastCardPlayedBy(Player lastPlayer){
        lastCardPlayedBy = lastPlayer;
    }

    public static Player getLastCardPlayedBy(){
        return lastCardPlayedBy;
    }
}
