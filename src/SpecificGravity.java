/**
 * Created by james on 14/09/2016.
 */
public class SpecificGravity{
    private String asStr;
    private Double value;

    public SpecificGravity(String SGStr){
        asStr = SGStr;
        value = parseSG(SGStr);
    }

    public static double parseSG(String SGString){
        String SGStringStripped = SGString.replaceAll("\\s+","");
        if (SGStringStripped.contains("-")){
//            Todo: This may be exactly the same as Hardness.parse and therefore redundant
//            Todo: Make TrumpCategory class that this function as parseRange(String rangeString)
            int SGIndex = SGStringStripped.indexOf('-') + 1;
            return Double.parseDouble(SGStringStripped.substring(SGIndex));
        }
        else{
            return Double.parseDouble(SGStringStripped);
        }
    }

    public double getValue(){
        return value;
    }

    public String toString(){
        return asStr;
    }
}
