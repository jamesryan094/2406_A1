package Cards;

import java.util.ArrayList;
import Trumps.*;

/**
 * Created by james on 6/09/2016.
 *
 */
public class MineralCard extends Card{


    private String chemistry, classification,
            crystalSystem, occurrence;

//    private ArrayList occurrence = new ArrayList();

//  Trump Categories
    private Cleavage cleavage;
    private CrustalAbundance crustalAbundance;
    private EconomicValue economicValue;
    private Hardness hardness;
    private SpecificGravity specificGravity;


    public MineralCard(ArrayList<String> cardAttributes) {
        super(cardAttributes);
        chemistry = cardAttributes.get(4);
        classification = cardAttributes.get(5);
        crystalSystem = cardAttributes.get(6);
        occurrence = cardAttributes.get(7);
//        for (int x = 0; x < occurrenceList.size(); ++x) {
//            occurrence.add(occurrenceList.get(x));
//        }
        hardness = new Hardness(cardAttributes.get(8));
        specificGravity = new SpecificGravity(cardAttributes.get(9));
        cleavage = new Cleavage(cardAttributes.get(10));
        crustalAbundance = new CrustalAbundance(cardAttributes.get(11));
        economicValue = new EconomicValue(cardAttributes.get(12));
    }

    public void printAttributes(){
        super.printAttributes();
        System.out.println("Chemistry: " + chemistry +
                "\nClassification: " + classification +
                "\nCrystal System: " + crystalSystem +
                "\nOccurrence: " + occurrence +
                "\nTrumps.Hardness: " + hardness.toString() +
                "\nSpecific Gravity: " + specificGravity.toString() +
                "\nTrumps.Cleavage: " + cleavage +
                "\nCrustal Abundance: " + crustalAbundance +
                "\nEconomic Value: " + economicValue.toString() +
                "\n--------------------");
    }

    public Cleavage getCleavage() {
        return cleavage;
    }

//    public Boolean compareEValue (Cards.MineralCard otherCard){
//        if(this.economicValue.ordinal() > otherCard.economicValue.ordinal()) {
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

}
