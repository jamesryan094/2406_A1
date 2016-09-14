/**
 * Created by james on 6/09/2016.
 * Acts as conduit between player and system by taking input and print output, controls flow of game.
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public ArrayList<Player> players = new ArrayList<>();
    public int numPlayers;
    public static int dealerIndex;


    public Game(int num){
        numPlayers = num;
        players = generatePlayers();
        System.out.println("Player array assigned to Game attribute; players");
    }

    public ArrayList<Player> generatePlayers() {
        ArrayList<Player> playerArray =  new ArrayList<>(numPlayers);
        for(int i =0; i < numPlayers; ++i){
           playerArray.add(new Player(i));
        }
        System.out.println("Players made, returning player array");
        return playerArray;
    }

    public void assignDealer(){
        Random rn = new Random();
        dealerIndex = rn.nextInt(numPlayers);
        players.get(dealerIndex).isDealer = true;
        System.out.println(dealerIndex);
    }

    public int getNumPlayers(){
        return numPlayers;
    }

    public void setNumPlayers(int num){
        numPlayers = num;
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

    public void printParty(){
        for(int i = 0; i < players.size();++i){
            System.out.println("Player: " + (i+1) +
                    "\nName: " + players.get(i).name +
                    "\nID: " + players.get(i).id +
                    "\nDealer: " + players.get(i).isDealer +
                    "\n--------------------");
        }
    }
}
