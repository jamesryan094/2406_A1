package Trumps;

/**
 * Created by james on 14/09/2016.
 */
public class Hardness extends  TrumpCategory {
    private double value;
    public Hardness(String hardnessString){
        asStr = hardnessString;
        value = parseRange(hardnessString);
    }

    public double getValue(){
        return value;
    }

    public boolean isGreaterThan(Hardness otherHardness){
        return getValue() > otherHardness.getValue();
    }
}
