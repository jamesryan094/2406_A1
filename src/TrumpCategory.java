/**
 * Created by james on 14/09/2016.
 * Parent class of trump category classes
 */
public class TrumpCategory {
//    public enum CATEGORY {CLEAVAGE, CRUSTAL_ABUNDANCE, ECONOMIC_CALUE, HARDNESS, SPECIFIC_GRAVITY, ERROR}
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
        return asStr;
    }

}
