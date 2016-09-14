import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 *
 */
public class MineralCard extends Card{


    private String chemistry, classification,
            crystalSystem,
            specificGravity, cleavage,
            crustalAbundance;
//            economicValue;
    AssignEconomicValue.ECONOMIC_VALUE economicValue;
//    double hardness;
//    String hardnessString, specificGravityString;
    Hardness hardness;
    private ArrayList occurrence = new ArrayList();


    public MineralCard(ArrayList cardAttributes) {
        super(cardAttributes);
        chemistry = (String) cardAttributes.get(4);
        classification = (String) cardAttributes.get(5);
        crystalSystem = (String) cardAttributes.get(6);
        ArrayList occurrenceList = (ArrayList) cardAttributes.get(7);
        for (int x = 0; x < occurrenceList.size(); ++x) {
            occurrence.add((String) occurrenceList.get(x));
        }
//        hardness = (String) cardAttributes.get(8);
//        hardness = this.AssignHardness((String) cardAttributes.get(8));
//        AssignHardness((String) cardAttributes.get(8));
//        hardness =Hardness.parseHardness((String) cardAttributes.get(8));
        hardness = new Hardness((String) cardAttributes.get(8));
        specificGravity = (String) cardAttributes.get(9);
        cleavage = (String) cardAttributes.get(10);
        crustalAbundance = (String) cardAttributes.get(11);
//        economicValue = (String) cardAttributes.get(12);
        economicValue = AssignEconomicValue.enumerateEconomicValue((String) cardAttributes.get(12));
    }

    public void printAttributes(){
        super.printAttributes();
        System.out.println("Chemistry: " + chemistry +
                "\nClassification: " + classification +
                "\nCrystal System: " + crystalSystem +
                "\nOccurrence: " + occurrence +
                "\nHardness: " + hardness.asStr +
                "\nSpecific Gravity: " + specificGravity +
                "\nCleavage: " + cleavage +
                "\nCrustal Abundance: " + crustalAbundance +
                "\nEconomic Value: " + economicValue +
                "\n--------------------");
    }

    public Boolean compareEValue (MineralCard otherCard){
        if(this.economicValue.ordinal() > otherCard.economicValue.ordinal()) {
            return true;
        }
        else{
            return false;
        }
    }

//    public void AssignHardness(String hardnessString){
//
//        this.hardnessString = hardnessString;
//        String hardnessStringStripped = hardnessString.replaceAll("\\s+", "");
//        if (hardnessStringStripped.contains("-")){
////            Assign the portion of the string following the hyphen as a double to hardness attribute
////            Todo: refactor into multiple lines for readability
//            this.hardness = Double.parseDouble(hardnessStringStripped.substring(hardnessStringStripped.indexOf('-')+1));
//        }
//        else{
//            this.hardness = Double.parseDouble(hardnessStringStripped);
//        }
//    }
}
