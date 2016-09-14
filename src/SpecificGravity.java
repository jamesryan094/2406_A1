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
            return Double.parseDouble(SGStringStripped.substring(SGStringStripped.indexOf('-')+1));
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
