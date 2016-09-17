import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 */
public class Player {
    private int id;
    ArrayList<Card> hand;
    boolean isDealer;
    final String[] NAMES = {"Matt", "Mark", "Luke", "John"};
    private String name;

    public Player(int id)
    {
        this.id = id;
        name = NAMES[id-1];
        isDealer = false;
        hand  = new ArrayList<>();
    }

    public Player(int id, String userName)
    {
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

//    public void playCard(int CardID){
//
//    }

}
