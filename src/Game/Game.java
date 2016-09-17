package Game; /**
 * Created by james on 6/09/2016.
 * Acts as conduit between player and system by taking input and print output, controls flow of game.
 */
import Cards.Card;
import Deck.Deck;
import Deck.GenerateDeckFromPLIST;
import Player.Player;

import java.util.ArrayList;
import java.util.Random;

class Game {
    private static final int NUM_CARDS_PER_HAND = 8;
    private final Deck deck;
    private Player[] players;
    private int numPlayers;
    private static int dealerIndex;
    private Player currentPlayer;

    Game(int num, String userName){
        this.deck = GenerateDeckFromPLIST.buildDeck();
        numPlayers = num;
        players = generatePlayers(userName);
        System.out.println("Player.Player array assigned to Game.Game attribute; players");
    }

    private Player[] generatePlayers(String userName) {
        Player[] playerArray =  new Player[numPlayers];
        playerArray[0] = new Player(0, userName);
        for(int i =1; i < numPlayers; ++i){
           playerArray[i] = new Player(i);
        }
//        System.out.println("Players made, returning player array");
//        playerArray shuffled to establish random playing order
//        Collections.shuffle(playerArray);
        return playerArray;
    }

    void assignDealer(){
        Random rn = new Random();
        dealerIndex = rn.nextInt(numPlayers);
        players[dealerIndex].setIsDealer(true);
        System.out.println(dealerIndex);
    }

    void initialDeal(){
        deck.removeRuleCards();
        deck.shuffle();
        for(Player player:this.players){
            ArrayList<Card> hand= deck.dealHand(NUM_CARDS_PER_HAND);
            player.setCurrentHand(hand);
        }
        this.currentPlayer = players[dealerIndex];
    }

    public int getNumPlayers(){
        return numPlayers;
    }

    public void setNumPlayers(int num){
        numPlayers = num;
    }


//    public void printParty(){
//        for(int i = 0; i < players.length;++i){
//            System.out.println("Player.Player: " + (i+1) +
//                    "\nName: " + players.get(i).name +
//                    "\nID: " + players.get(i).id +
//                    "\nDealer: " + players.get(i).isDealer +
//                    "\n--------------------");
//        }
//    }


    Player getCurrentPlayer() {
        int currentPlayerId = currentPlayer.getId();
        if (currentPlayerId == players.length - 1){
            currentPlayer = players[0];
        }
        else{
            currentPlayer = players[currentPlayerId + 1];
        }
        return currentPlayer;
    }

    Player getDealer() {
        return players[dealerIndex];
    }

//    public void setCurrentPlayer(Player.Player currentPlayer) {
//        this.currentPlayer = currentPlayer;
//    }
}
