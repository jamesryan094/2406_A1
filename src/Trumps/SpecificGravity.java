package Trumps;

/**
 * Assigns upper value in Specific Gravity range as Specific Gravity value.
 * Provides functionality for comparing two values of Specific Gravity.
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

    public boolean isGreaterThan(SpecificGravity otherSpecificGravity){
        return getValue() > otherSpecificGravity.getValue();
    }

}
