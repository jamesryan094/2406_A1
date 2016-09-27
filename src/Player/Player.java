package Player;

import Cards.Card;

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

    public Player(int id)
    {
        isHuman = false;
        this.id = id;
        name = NAMES[id-1];
        isDealer = false;
        hand  = new ArrayList<>();
    }

    public Player(int id, String userName)
    {
        isHuman = true;
        this.id = id;
        name = userName;
        isDealer = false;
        hand  = new ArrayList<>();
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

    public boolean isHuman(){
        return this.isHuman;
    }

    public void displayHandDetails(){
        for(int i=0; i < this.hand.size(); ++i){
            hand.get(i).printAttributes();
        }
    }
    public ArrayList<Card> getHand(){
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

    public boolean equals(Player otherPlayer){
        if ((this.getName().equals(otherPlayer.getName())) && (this.getId()==otherPlayer.getId())){
            return true;
        }
        else{
            return false;
        }
    }

    public abstract Card playCard(int cardChoice);

    public abstract String getTrumpCategoryChoice();
}
