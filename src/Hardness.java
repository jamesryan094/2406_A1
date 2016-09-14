/**
 * Created by james on 14/09/2016.
 */
public class Hardness {
    double value;
    String asStr;
    public Hardness(String hardnessString){
        asStr = hardnessString;
        value = parseHardness(hardnessString);
    }

    public static double parseHardness(String hardnessString){

//        asStr = hardnessString;
        String hardnessStringStripped = hardnessString.replaceAll("\\s+", "");
        if (hardnessStringStripped.contains("-")){
//            Assign the portion of the string following the hyphen as a double to hardness attribute
//            Todo: refactor into multiple lines for readability
            return Double.parseDouble(hardnessStringStripped.substring(hardnessStringStripped.indexOf('-')+1));
        }
        else{
            return Double.parseDouble(hardnessStringStripped);
        }
    }
}
