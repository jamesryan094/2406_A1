package Player;

import Cards.Card;
import Deck.Deck;

import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 */
public abstract class Player {
    private int id;
    private ArrayList<Card> hand;
    private boolean isDealer;
    private final String[] NAMES = {"Matt", "Mark", "Luke", "John"};
    private String name;
    private boolean isHuman;
    private boolean hasPassed;

    public Player(int id) {
        isHuman = false;
        this.id = id;
        name = NAMES[id - 1];
        isDealer = false;
        hand = new ArrayList<>();
        hasPassed = false;
    }

    public Player(int id, String userName) {
        isHuman = true;
        this.id = id;
        name = userName;
        isDealer = false;
        hand = new ArrayList<>();
        hasPassed = false;
    }

    public void setCurrentHand(ArrayList<Card> currentHand) {
        this.hand = currentHand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

//    public void playCard(int CardID){
//
//    }

    public boolean isHuman() {
        return this.isHuman;
    }

    public void displayHandDetails() {
        for (int i = 0; i < this.hand.size(); ++i) {
            hand.get(i).printAttributes();
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

//    public void displayHandMenu(){
//        int menuChoice;
//        Scanner keys = new Scanner(System.in);
//        for(int i=0; i < this.hand.size(); ++i){
//            System.out.println("("+ i + ") " + hand.get(i).getTitle());
//        }
//        menuChoice = keys.nextInt();
//        keys.nextLine();
//        hand.get(menuChoice).printAttributes();
//    }

    public boolean equals(Player otherPlayer) {
        if ((this.getName().equals(otherPlayer.getName())) && (this.getId() == otherPlayer.getId())) {
            return true;
        } else {
            return false;
        }
    }

    public abstract Card playCard(int cardChoice);

    public abstract String getTrumpCategoryChoice();

    public abstract Card playAnyMineralCard();

    public abstract boolean hasPlayableCards(Card lastPlayedCard, String currentTrump);

    //Todo: drawCard may not be abstract.
    public abstract void drawCard(Deck deck);

    public boolean getHasPassed() {
        return hasPassed;
    }


    public void setHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }

    public boolean hasMineralCards() {
        for (int i = 0; i < getHand().size(); ++i) {
            if (getHand().get(i).getCardType().equals("play")) {
                return true;
            }
        }
        return false;
    }

    public abstract Card playAnyCard();

    public boolean hasCombo() {
        int numComboCards = 0;
        for (Card card :
                this.hand) {
            if (card.getTitle().equals("The Geophysicist") || card.getTitle().equals("Magnetite")) {
                ++numComboCards;
            }
        }
        return numComboCards == 2;
    }

    public Card playCombo() {
        int magnetiteIndex = -1;
        int geophysIndex = -1;
        Card mag = null;
        Card geoPhys = null;

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getTitle().equals("The Geophysicist")){
//                geophysIndex = i;
                geoPhys = hand.get(i);
            }
            if (hand.get(i).getTitle().equals("Magnetite")){
//                magnetiteIndex = i;
                mag = hand.get(i);
            }
        }
        hand.remove(geoPhys);
        hand.remove(mag);
        return mag;
    }
}
