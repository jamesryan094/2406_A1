package Trumps;

/**
 * Created by james on 14/09/2016.
 */
public class SpecificGravity extends TrumpCategory {
    private Double value;

    public SpecificGravity(String SGStr){
        asStr = SGStr;
        value = parseRange(SGStr);
    }


    public double getValue(){
        return value;
    }

}