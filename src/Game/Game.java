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
    public static final String TRUMP_HIERARCHIES = "Information on trump categories:\n\n" +
            "Hardness: relates to Moh’s hardness scale of minerals from 1 to 10. Where a\n" +
            "range of values is presented, the highest value should be used.\n\n" +
            "Specific Gravity: in grams per cubic centimeter. Where a range of values is\n" +
            "presented, the highest value should be used.\n\n" +
            "Cleavage: refers to the number of cleavage planes and how well the planes\n" +
            "are typically expressed in the crystal. For example, “1 perfect, 2 poor” means\n" +
            "the mineral has 1 perfect cleavage plane, and 2 poor cleavage planes. The\n" +
            "order of ranking from lowest to highest is:\n" +
            "none < poor/none < 1 poor < 2 poor < 1 good < 1 good, 1 poor < 2\n" +
            "good < 3 good < 1 perfect < 1 perfect, 1 good < 1 perfect, 2 good < 2\n" +
            "perfect, 1 good < 3 perfect < 4 perfect < 6 perfect.\n\n" +
            "Crustal Abundance: is ranked from lowest to highest as:\n" +
            "ultratrace < trace < low < moderate < high < very high.\n\n" +
            "Economic Value: is ranked from lowest to highest as:\n" +
            "trivial < low < moderate < high < very high < I’m rich!\n";
    private final Deck deck;
    final static int MIN_PLAYERS = 3;
    final static int MAX_PLAYERS = 5;
    private Player[] players;
    private ArrayList<Player> playersInRound = new ArrayList<>();
    private int numPlayers;
    private static int dealerIndex;
    private Player currentPlayer;
    private boolean isWon;
//    private int roundNum;
    private boolean humanPlayedCard = false;
    private boolean cardHasBeenPlayed = false;
    private Card lastPlayedCard;
    private TrumpCategory currentTrumpValue;
    private String currentTrumpCategory;
    private int numPasses;
    private Player lastPlayer;
    private ArrayList<Player> winners = new ArrayList<>();
    private boolean isFirstTurn;
    private boolean newRound;
    private boolean comboPlayed;
    final static String INSTRUCTIONS = "How to play:\n" +
            "1. A dealer (randomly chosen) shuffles the cards and deals each player 8\n" +
            "cards. Each player can look at their cards, but should not show them to\n" +
            "other players. The remaining card pack is placed face down on the table.\n\n" +
            "2. The player to the left of the dealer goes first by placing a mineral card on\n" +
            "the table. The player must state the mineral name, one of the five trump\n" +
            "categories (i.e., either Hardness, Specific Gravity, Cleavage, Crustal\n" +
            "Abundance, or Economic Value), and the top value of that category. For\n" +
            "example, a player placing the Glaucophane card may state “Glaucophane,\n" +
            "Specific Gravity, 3.2”\n\n" +
            "3. The player next to the left takes the next turn. This player must play a\n" +
            "mineral card that has a higher value in the trump category than the card\n" +
            "played by the previous player. In the case of the example of the\n" +
            "Glaucophane card above, the player must place a card that has a value for\n" +
            "specific gravity above 3.2. The game continues with the next player to the\n" +
            "left, and so on.\n\n" +
            "4. If a player does not have any mineral cards that are of higher value for the\n" +
            "specific trump category being played, then the player must pass and pick up\n" +
            "one card from the card pack on the table. The player then cannot play again\n" +
            "until all but one player has passed, or until another player throws a\n" +
            "supertrump card to change the trump category, as described below. A player\n" +
            "is allowed to pass even if they still hold cards that could be played. \n\n" +
            "5. If the player has a supertrump card (The Miner, The Geologist, The\n" +
            "Geophysicist, The Petrologist, The Mineralogist, The Gemmologist) they\n" +
            "may play this card at any of their turns. By placing a supertrump card, the\n" +
            "player changes the trump category according to the instructions on the\n" +
            "supertrump card. At this stage, any other player who had passed on the previous\n" +
            "round is now able to play again. If a player happens to hold both The\n" +
            "Geophysicist card and the Magnetite card in their hand, then that player can\n" +
            "place both cards together to win the round.\n\n" +
            "6. The game continues with players taking turns to play cards until all but one\n" +
            "player has passed. The last player then gets to lead out the next round and\n" +
            "chooses the trump category to be played.\n\n" +
            "7. The winner of the game is the first player to lose all of their cards. The\n" +
            "game continues until all but one player (i.e., the loser) has lost their cards.\n";


    Game(int num, String userName){
        this.deck = GenerateDeckFromPLIST.buildDeck();
        numPlayers = num;
        players = generatePlayers(userName);
        isWon = false;
        numPasses = 0;
        isFirstTurn = true;
        comboPlayed = false;
//        resetPlayersInRound clears the attribute's list then repopulates. Is called here to populate, clearing has no effect on empty list
        resetPlayersInRound();
//        roundNum = 0;
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
//        Todo: refactor
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
//        ArrayList<Card> userHand = new ArrayList<>();
//        userHand.add(deck.getGeophys());
//        userHand.add(deck.getMagnetite());
//        userHand.addAll(deck.dealHand(NUM_CARDS_PER_HAND));
//        players[2].setCurrentHand(userHand);
//        for (int i = 0; i < players.length-1; i++) {
//            ArrayList<Card> hand= deck.dealHand(NUM_CARDS_PER_HAND);
//            players[i].setCurrentHand(hand);
//        }
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
        return currentPlayer;
    }

    protected Player getCurrentPlayer(){
        return currentPlayer;
    }
    protected void setCurrentPlayer(Player currentPlayer){
        this.currentPlayer = currentPlayer;
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

//    public int getRoundNum(){
//        return roundNum;
//    }
//    public void incrementRoundNum(){
//        roundNum+=1;
//    }

    boolean isHumanUp(){
        if (this.getCurrentPlayer().isHuman()){
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
    public void setLastPlayedAttributes(Card lastPlayedCard) {
        lastPlayer = currentPlayer;
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
        setLastPlayedAttributes(chosenCard);
        setHumanPlayedCard();
        setCardHasBeenPlayed(true);
    }

    public void playTrumpCard(int cardChoice) {
//        When a user plays a Trump Card:
        Card chosenCard = currentPlayer.playCard(cardChoice);
//        String trumpChoice = chosenCard.getTitle();
        String trumpChoice = getTrumpChoiceFromTrumpCard(chosenCard.getTitle());
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedAttributes(chosenCard);
        setHumanPlayedCard();
//        resetRound();
        resetRoundTrump();
        setCardHasBeenPlayed(true);
    }

//    public void playTrumpCard() {
////    When a robot plays first:
//
//        Card cardChoice = currentPlayer.playAnyMineralCard();
//        String trumpChoice;
//        if(cardChoice.getCardType().equals("play")||cardChoice.isGeologist()){
//            trumpChoice = currentPlayer.getTrumpCategoryChoice();
//        }
//        else{
//            trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
//        }
//        setCurrentTrumpCategory(trumpChoice);
//        setLastPlayedAttributes(cardChoice);
//        setCardHasBeenPlayed(true);
//
//    }
    public void playFirstTurn(){
//        When a robot plays first card of round:
        String trumpChoice;
        Card cardChoice;
        resetComboPlayed();
        //first turn of game
        if (isFirstTurn){
            cardChoice = currentPlayer.playAnyMineralCard();
            trumpChoice = currentPlayer.getTrumpCategoryChoice();
        }
        //first turn of ROUND
        else {
            cardChoice = currentPlayer.playAnyCard();
            if (cardChoice.getCardType().equals("play") || cardChoice.isGeologist()){
                trumpChoice = currentPlayer.getTrumpCategoryChoice();
            }
            else {
                trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
            }
        }
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedAttributes(cardChoice);
        setCardHasBeenPlayed(true);

    }

    public String getTrumpChoiceFromTrumpCard(String trumpTitle){
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
        if (currentPlayer.hasCombo()){
            playCombo();
        }
        else {

            boolean haveCard = false;
            Card cardChoice = null;
            for(int i=0;i<currentPlayer.getHand().size();++i){
                if (currentPlayer.getHand().get(i).canPlayOn(lastPlayedCard, currentTrumpCategory)&&!haveCard){
                    cardChoice = currentPlayer.playCard(i);
                    haveCard=true;
                }
            }
            if (cardChoice.getCardType().equals("trump")) {
                String trumpChoice;
                if(cardChoice.isGeologist()){
                    trumpChoice = currentPlayer.getTrumpCategoryChoice();
                }else {
                    trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
                }
                setCurrentTrumpCategory(trumpChoice);
                resetRoundTrump();
            }
            setLastPlayedAttributes(cardChoice);
        }

    }

    public void passTurn() {
        currentPlayer.setHasPassed(true);
        removePlayerFromRound();
        if((deck.getCards().size() != 0)&&currentPlayer.getHand().size()!=0) {
//            System.out.println(currentPlayer.getName() + " draws a card!");
            currentPlayer.drawCard(deck);
        }
        else{
//            System.out.println("Deck Empty!");
        }
    }

    public int getNumPasses() {
        return numPasses;
    }
    public void resetNumPasses(){
        numPasses = 0;
    }

    public void incrementNumPasses() {
        numPasses +=1 ;
//        System.out.println("in incrementNumPasses(), numPasses incremented, numPasses = " + numPasses);
    }

    public void resetRound() {
//        todo: refactor to use get/setters
//        if(!winners.contains(currentPlayer))
        resetNumPasses();
        resetPlayersInRound();
        setCardHasBeenPlayed(false);
        for (Player player:players){
            if(!winners.contains(player)) {
                player.setHasPassed(false);
            }
        }
    }
    public void resetRoundTrump() {
        resetNumPasses();
        resetPlayersInRound();
        for (Player player:players){
            player.setHasPassed(false);
        }
    }

    public Player getLastPlayerInRound() {
        return playersInRound.get(0);
    }

    public void removePlayerFromRound() {
        playersInRound.remove(currentPlayer);
    }

    public void resetPlayersInRound(){
        playersInRound.clear();
//        for(Player player:getPlayers()){
//            playersInRound.add(player);
//        }
        for(int i = 0; i < getPlayers().length; ++i){
            playersInRound.add(getPlayers()[i]);
        }
    }

    public boolean checkWinner() {
        if(getCurrentPlayer().getHand().size()==0){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<Player> getWinners(){
        return winners;
    }

    public void updateWinners() {
        if(!winners.contains(currentPlayer)){
            winners.add(currentPlayer);
//            System.out.println("Congratulations " + currentPlayer.getName() + "! You have been added to the winner list!");
//            currentPlayer.setHasPassed(true);
//            currentPlayer.setHasPassed(true);
        }
        if(winners.size() == getPlayers().length-1){
            setIsWon();
//            System.out.println("Game Completed! Game over!");
        }
//        if (winners.size() == getPlayers().length - 1){
//            setIsWon();
//            System.out.println("Game Completed! Game over!");
//        }
    }

    void setIsWon(){
        isWon = true;
    }

    public void setFirstTurn(boolean firstTurn) {
        isFirstTurn = firstTurn;
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }

    public boolean isNewRound() {
        return (numPasses == numPlayers - 1 - winners.size());

    }

    public void playCombo() {
        this.lastPlayedCard = currentPlayer.playCombo();
        comboPlayed = true;
        resetRound();
    }

    public boolean comboPlayed() {
        return comboPlayed;
    }

    public void resetComboPlayed() {
        comboPlayed = false;
    }
}
