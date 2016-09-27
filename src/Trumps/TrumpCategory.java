package Trumps;

/**
 * Created by james on 14/09/2016.
 * Parent class of trump category classes
 * Child Classes: (Trumps.Cleavage, Trumps.CrustalAbundance, Trumps.EconomicValue, Trumps.Hardness, Trumps.SpecificGravity)
 */
public class TrumpCategory {
    public enum CATEGORY {CLEAVAGE, CRUSTAL_ABUNDANCE, ECONOMIC_VALUE, HARDNESS, SPECIFIC_GRAVITY, ERROR}
    String[] trumpStrings = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};
    protected String asStr;

    protected double parseRange(String rangeString){
        String rangeStringStripped = rangeString.replaceAll("\\s+", "");
        if (rangeStringStripped.contains("-")){
            int upperRangeIndex = rangeStringStripped.indexOf('-') + 1;
            return Double.parseDouble(rangeStringStripped.substring(upperRangeIndex));
        }
        else{
            return Double.parseDouble(rangeStringStripped);
        }
    }


    public String toString(){
////    Todo: This is really more of a getter than a toString(). Ask if this is okay!
        return asStr;
    }

}
