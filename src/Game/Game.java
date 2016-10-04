package Game;
import Cards.Card;
import Deck.Deck;
import Deck.GenerateDeckFromPLIST;

import Player.Player;
import Player.HumanPlayer;
import Player.NonHumanPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 *
 * Created by james on 6/09/2016.
 */
class Game {
    private static final int NUM_CARDS_PER_HAND = 8;
    static final String TRUMP_HIERARCHIES = "Information on trump categories:\n\n" +
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
    private int numPlayers;
    private static int dealerIndex;
    private Player currentPlayer;
    private boolean isWon;
    private boolean humanPlayedCard = false;
    private Card lastPlayedCard;
    private String currentTrumpCategory;
    private int numPasses;
    private ArrayList<Player> winners = new ArrayList<>();
    private boolean isFirstTurn;
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
    }

    private Player[] generatePlayers(String userName) {
        Player[] playerArray =  new Player[numPlayers];
        playerArray[0] = new HumanPlayer(userName);
        for(int i =1; i < numPlayers; ++i){
           playerArray[i] = new NonHumanPlayer(i);
        }
        return playerArray;
    }

    void assignDealer(){
        Random rn = new Random();
        dealerIndex = rn.nextInt(numPlayers);
        players[dealerIndex].setIsDealer(true);
    }


    void initialDeal(){
        deck.removeRuleCards();
        deck.shuffle();

    //For testing Combo mechanic.
//        ArrayList<Card> userHand = new ArrayList<>();
//        userHand.add(deck.getGeophys());
//        userHand.add(deck.getMagnetite());
//        userHand.addAll(deck.dealHand(NUM_CARDS_PER_HAND));
//        players[0].setCurrentHand(userHand);
//        for (int i = 1; i < players.length; i++) {
//            ArrayList<Card> hand= deck.dealHand(NUM_CARDS_PER_HAND);
//            players[i].setCurrentHand(hand);
//        }

        for(Player player:this.players){
            ArrayList<Card> hand= deck.dealHand(NUM_CARDS_PER_HAND);
            player.setCurrentHand(hand);
        }
        this.currentPlayer = players[dealerIndex];
    }


    Player incrementCurrentPlayer() {
        int currentPlayerId = currentPlayer.getId();
        if (currentPlayerId == players.length - 1){
            currentPlayer = players[0];
        }
        else{
            currentPlayer = players[currentPlayerId + 1];
        }
        return currentPlayer;
    }

    Player getCurrentPlayer(){
        return currentPlayer;
    }

    Player getDealer() {
        return players[dealerIndex];
    }

    Player[] getPlayers(){
        return players;
    }

    boolean isWon() {
        return isWon;
    }

    boolean isHumanUp(){
        return this.getCurrentPlayer().isHuman();
    }

    boolean getHumanPlayedCard(){
        return humanPlayedCard;
    }

    void setHumanPlayedCard() {
        humanPlayedCard = true;
    }

    void resetHumanPlayedCard(){
        humanPlayedCard = false;
    }

    Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    void setLastPlayedAttributes(Card lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
    }

    String getCurrentTrumpCategory() {
        return currentTrumpCategory;
    }

    private void setCurrentTrumpCategory(String currentTrumpCategory) {
        this.currentTrumpCategory = currentTrumpCategory;
    }

    void playFirstTurn(int cardChoice, String trumpChoice) {
//        When a user is playing a MineralCard Or 'The Geologist':
        Card chosenCard = currentPlayer.playCard(cardChoice);
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedAttributes(chosenCard);
        setHumanPlayedCard();
    }

    void playTrumpCard(int cardChoice) {
//        When a user plays a Trump Card:
        Card chosenCard = currentPlayer.playCard(cardChoice);
        String trumpChoice = getTrumpChoiceFromTrumpCard(chosenCard.getTitle());
        setCurrentTrumpCategory(trumpChoice);
        setLastPlayedAttributes(chosenCard);
        setHumanPlayedCard();
        resetRoundTrump();
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
    void playFirstTurn(){
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


    void playTurn() {
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
            if (cardChoice != null && cardChoice.getCardType().equals("trump")) {
                String trumpChoice;
                if (cardChoice.isGeologist()) {
                    trumpChoice = currentPlayer.getTrumpCategoryChoice();
                } else {
                    trumpChoice = getTrumpChoiceFromTrumpCard(cardChoice.getTitle());
                }
                setCurrentTrumpCategory(trumpChoice);
                resetRoundTrump();
            }
            setLastPlayedAttributes(cardChoice);
        }

    }

    void passTurn() {
        currentPlayer.setHasPassed(true);
//        removePlayerFromRound();
        if((deck.getCards().size() != 0)&&currentPlayer.getHand().size()!=0) {
            currentPlayer.drawCard(deck);
        }
    }

    private void resetNumPasses(){
        numPasses = 0;
    }

    void incrementNumPasses() {
        numPasses +=1 ;
    }

    void resetRound() {
        resetNumPasses();
//        resetPlayersInRound();
        for (Player player:players){
            if(!winners.contains(player)) {
                player.setHasPassed(false);
            }
        }
    }

    void resetRoundTrump() {
        resetNumPasses();
//        resetPlayersInRound();
        for (Player player:players){
            player.setHasPassed(false);
        }
    }

//    private void removePlayerFromRound() {
//        playersInRound.remove(currentPlayer);
//    }

//    private void resetPlayersInRound(){
//        playersInRound.clear();
//        for(int i = 0; i < getPlayers().length; ++i){
//            playersInRound.add(getPlayers()[i]);
//        }
//    }

    boolean checkWinner() {
        return getCurrentPlayer().getHand().size() == 0;
    }

    ArrayList<Player> getWinners(){
        return winners;
    }

    void updateWinners() {
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

    private void setIsWon(){
        isWon = true;
    }

    void setFirstTurn(boolean firstTurn) {
        isFirstTurn = firstTurn;
    }

    boolean isFirstTurn() {
        return isFirstTurn;
    }

    boolean isNewRound() {
        return (numPasses == numPlayers - 1 - winners.size());

    }

    void playCombo() {
        this.lastPlayedCard = currentPlayer.playCombo();
        comboPlayed = true;
        resetRound();
    }

    boolean comboPlayed() {
        return comboPlayed;
    }

    void resetComboPlayed() {
        comboPlayed = false;
    }
}
