import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 *
 */
public class MineralCard extends Card{


    private String chemistry, classification,
            crystalSystem;

    Cleavage cleavage;

    CrustalAbundance crustalAbundance;
    EconomicValue economicValue;
    Hardness hardness;
    SpecificGravity specificGravity;
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
        hardness = new Hardness((String) cardAttributes.get(8));
        specificGravity = new SpecificGravity((String) cardAttributes.get(9));
//        cleavage = (String) cardAttributes.get(10);
        cleavage = new Cleavage((String) cardAttributes.get(10));
        crustalAbundance = new CrustalAbundance((String) cardAttributes.get(11));
        economicValue = new EconomicValue((String) cardAttributes.get(12));
    }

    public void printAttributes(){
        super.printAttributes();
        System.out.println("Chemistry: " + chemistry +
                "\nClassification: " + classification +
                "\nCrystal System: " + crystalSystem +
                "\nOccurrence: " + occurrence +
                "\nHardness: " + hardness.toString() +
                "\nSpecific Gravity: " + specificGravity.toString() +
                "\nCleavage: " + cleavage +
                "\nCrustal Abundance: " + crustalAbundance +
                "\nEconomic Value: " + economicValue.toString() +
                "\n--------------------");
    }

//    public Boolean compareEValue (MineralCard otherCard){
//        if(this.economicValue.ordinal() > otherCard.economicValue.ordinal()) {
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

}
