package Game; /**
 * Created by james on 6/09/2016.
 * Acts as conduit between player and system by taking input and print output, controls flow of game.
 */
import Cards.Card;
import Deck.Deck;
import Deck.GenerateDeckFromPLIST;
import Player.Player;

import Player.HumanPlayer;
import Player.NonHumanPlayer;
import Trumps.TrumpCategory;

import java.util.ArrayList;
import java.util.Random;

class Game {
    private static final int NUM_CARDS_PER_HAND = 8;
    private final Deck deck;
    private Player[] players;
    private int numPlayers;
    private static int dealerIndex;
    private Player currentPlayer;
    private boolean isWon;
    private int roundNum;
    private boolean humanPlayedCard = false;
    private boolean cardHasBeenPlayed = false;
    private Card lastPlayedCard;
    private TrumpCategory currentTrumpValue;
    private String currentTrumpCategory;


    Game(int num, String userName){
        this.deck = GenerateDeckFromPLIST.buildDeck();
        numPlayers = num;
        players = generatePlayers(userName);
        isWon = false;
        roundNum = 0;
//        System.out.println("Player array assigned to Game attribute; players");
    }

    private Player[] generatePlayers(String userName) {
        Player[] playerArray =  new Player[numPlayers];
        playerArray[0] = new HumanPlayer(userName);
        for(int i =1; i < numPlayers; ++i){
           playerArray[i] = new NonHumanPlayer(i);
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
        System.out.println("Dealer: " + players[dealerIndex].getName());
    }

    void testAssignDealer(){
        dealerIndex = numPlayers-1;
        players[dealerIndex].setIsDealer(true);
        System.out.println("Dealer: " + players[dealerIndex].getName());
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


    protected Player incrementCurrentPlayer() {
        int currentPlayerId = currentPlayer.getId();
        if (currentPlayerId == players.length - 1){
            currentPlayer = players[0];
        }
        else{
            currentPlayer = players[currentPlayerId + 1];
        }
        System.out.println("Current Player: " + currentPlayer.getName());
        return currentPlayer;
    }

    protected Player getCurrentPlayer(){
        return currentPlayer;
    }

    Player getDealer() {
        return players[dealerIndex];
    }

    Player[] getPlayers(){
        return players;
    }

    public boolean isWon() {
        return isWon;
    }

    public int getRoundNum(){
        return roundNum;
    }
    public void incrementRoundNum(){
        roundNum+=1;
    }

    boolean isHumanUp(){
        if (getCurrentPlayer().isHuman()){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean getHumanPlayedCard(){
        return humanPlayedCard;
    }
    public void setHumanPlayedCard() {
        humanPlayedCard = true;
    }
    public void resetHumanPlayedCard(){
        humanPlayedCard = false;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }
    public void setLastPlayedCard(Card lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
    }

    public boolean cardHasBeenPlayed() {
        return cardHasBeenPlayed;
    }
    public void setCardHasBeenPlayed(boolean cardHasBeenPlayed) {
        this.cardHasBeenPlayed = cardHasBeenPlayed;
    }

    public String getCurrentTrumpCategory() {
        return currentTrumpCategory;
    }

    public void setCurrentTrumpCategory(String currentTrumpCategory) {
        this.currentTrumpCategory = currentTrumpCategory;
    }

    public TrumpCategory getCurrentTrumpValue() {
        return currentTrumpValue;
    }

    public void setCurrentTrumpValue(TrumpCategory currentTrumpValue) {
        this.currentTrumpValue = currentTrumpValue;
    }

    public void playFirstTurn(int cardChoice, String trumpChoice) {
//        When a user is playing a MineralCard Or 'The Geologist':
        Card chosenCard = currentPlayer.playCard(cardChoice);
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedCard(chosenCard);
        setHumanPlayedCard();
        setCardHasBeenPlayed(true);
    }

    public void playFirstTurn(int cardChoice) {
//        When a player plays a Trump Card:
        Card chosenCard = currentPlayer.playCard(cardChoice);
//        String trumpChoice = chosenCard.getTitle();
        String trumpChoice = getTrumpChoiceFromTrumpCard(chosenCard.getTitle());
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedCard(chosenCard);
        setHumanPlayedCard();
        setCardHasBeenPlayed(true);
    }

    public void playFirstTurn() {
//    When a robot plays first:

        Card cardChoice = currentPlayer.playAnyCard();
        String trumpChoice;
        if(cardChoice.getCardType().equals("play")||cardChoice.isGeologist()){
            trumpChoice = currentPlayer.getTrumpCategoryChoice();
        }else{
            trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
        }
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedCard(cardChoice);
        setCardHasBeenPlayed(true);

    }

    private String getTrumpChoiceFromTrumpCard(String trumpTitle){
        String trumpChoice;
        switch (trumpTitle){
            case "The Miner":
                trumpChoice = "Economic Value";
                break;
            case "The Petrologist":
                trumpChoice = "Crustal Abundance";
                break;
            case "The Gemmologist":
                trumpChoice = "Hardness";
                break;
            case "The Mineralogist":
                trumpChoice = "Cleavage";
                break;
            case "The Geophysicist":
                trumpChoice = "Specific Gravity";
                break;
            default:
                System.out.println("Error GetTrumpChoiceFromTrumpCard()");
                trumpChoice = "The Petrologist";
                break;
        }
        return trumpChoice;
    }


    public void playTurn() {
        boolean haveCard = false;
        Card cardChoice = null;
        for(int i=0;i<currentPlayer.getHand().size();++i){
            if (currentPlayer.getHand().get(i).canPlayOn(lastPlayedCard, currentTrumpCategory)&&!haveCard){
                cardChoice = currentPlayer.playCard(i);
                haveCard=true;
                }
            }
        if (haveCard) {
//            currentPlayer.playCard(cardChoice);
            if (cardChoice.getCardType().equals("trump")) {
                String trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
                setCurrentTrumpCategory(trumpChoice);
            }
            setLastPlayedCard(cardChoice);

        }
        else{
            System.out.println(currentPlayer.getName() + " Must Pass");

        }


    }
}
