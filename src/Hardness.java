/**
 * Created by james on 14/09/2016.
 */
public class Hardness {
    private double value;
    private String asStr;
    public Hardness(String hardnessString){
        asStr = hardnessString;
        value = parseHardness(hardnessString);
    }

    public static double parseHardness(String hardnessString){

//        asStr = hardnessString;
        String hardnessStringStripped = hardnessString.replaceAll("\\s+", "");
        if (hardnessStringStripped.contains("-")){
//            Assign the portion of the string following the hyphen as a double to hardness attribute
            int hardnessIndex = hardnessStringStripped.indexOf('-') + 1;
            return Double.parseDouble(hardnessStringStripped.substring(hardnessIndex));
        }
        else{
            return Double.parseDouble(hardnessStringStripped);
        }
    }
    public double getValue(){
        return value;
    }

    public String toString(){
//        Todo: This is really more of a getter than a toString(). Ask if this is okay!
        return asStr;
    }
}
