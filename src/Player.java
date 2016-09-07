import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 */
public class Player {
    int id;
    ArrayList<Card> hand = new ArrayList<>();
    boolean isDealer;
    final String[] NAMES = {"Matt", "Mark", "Luke", "John", "Bob"};
    String name;

    public Player(int id)
    {
        this.id = id;
        name = NAMES[id];
        isDealer = false;
    }


}
