import java.util.ArrayList;

/**
 * Created by james on 6/09/2016.
 *
 */
public class MineralCard extends Card{


    private String chemistry, classification,
            crystalSystem, hardness,
            specificGravity, cleavage,
            crustalAbundance, economicValue;
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
        hardness = (String) cardAttributes.get(8);
        specificGravity = (String) cardAttributes.get(9);
        cleavage = (String) cardAttributes.get(10);
        crustalAbundance = (String) cardAttributes.get(11);
        economicValue = (String) cardAttributes.get(12);
    }

    public void printAttributes(){
        super.printAttributes();
        System.out.println("Chemistry: " + chemistry +
                "\nClassification: " + classification +
                "\nCrystal System: " + crystalSystem +
                "\nOccurrence: " + occurrence +
                "\nHardness: " + hardness +
                "\nSpecific Gravity: " + specificGravity +
                "\nCleavage: " + cleavage +
                "\nCrustal Abundance: " + crustalAbundance +
                "\nEconomic Value: " + economicValue +
        "\n--------------------");
    }
}
