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
                "\nHardness: " + hardness.toString() +
                "\nSpecific Gravity: " + specificGravity.toString() +
                "\nCleavage: " + cleavage +
                "\nCrustal Abundance: " + crustalAbundance +
                "\nEconomic Value: " + economicValue.toString() +
                "\n--------------------");
    }


    public boolean canPlayOn(Card otherCard, String currentTrumpCategory){
        if (otherCard.getCardType().equals("trump")){
            return true;
        }
        MineralCard otherMCard = (MineralCard) otherCard;
        switch (currentTrumpCategory){
            case "Cleavage":
                Cleavage myCleavage = getCleavage();
                Cleavage otherCleavage = otherMCard.getCleavage();
                if(myCleavage.isGreaterThan(otherCleavage)){
                    return true;
                }
                break;
            case "Crustal Abundance":
                CrustalAbundance myCA = getCrustalAbundance();
                CrustalAbundance otherCA = otherMCard.getCrustalAbundance();
                if(myCA.isGreaterThan(otherCA)){
                    return true;
                }
                break;
            case "Economic Value":
                EconomicValue myEV = getEconomicValue();
                EconomicValue otherEV = otherMCard.getEconomicValue();
                if(myEV.isGreaterThan(otherEV)){
                    return true;
                }
                break;
            case "Hardness":
                Hardness myHardness = getHardness();
                Hardness otherHardness = otherMCard.getHardness();
                if(myHardness.isGreaterThan(otherHardness)){
                    return true;
                }
                break;
            case "Specific Gravity":
                SpecificGravity mySG = getSpecificGravity();
                SpecificGravity otherSG = otherMCard.getSpecificGravity();
                if(mySG.isGreaterThan(otherSG)){
                    return true;
                }
                break;
            default:
                System.out.println("Error");
                return false;
        }
        return false;
    }



    public TrumpCategory getCurrentTrumpValue(String currentTrumpCategory, Card cardInPlay){
        String[] trumpStrings = {"Cleavage", "Crustal Abundance", "Economic Value", "Hardness", "Specific Gravity"};
        TrumpCategory valueAsString;
        switch (currentTrumpCategory){
            case "Cleavage":
                valueAsString = this.cleavage;
                break;
            case "Crustal Abundance":
                valueAsString = this.crustalAbundance;
                break;
            case "Economic Value":
                valueAsString = this.economicValue;
                break;
            case "Hardness":
                valueAsString = this.hardness;
                break;
            case "Specific Gravity":
                valueAsString = this.specificGravity;
                break;
            default:
                System.out.println("Error");
                valueAsString = this.cleavage;
                break;
        }
        return valueAsString;
    }


    public String getCurrentTrumpValueAsString(String currentTrumpCategory){
        String valueAsString;
        switch (currentTrumpCategory){
            case "Cleavage":
                valueAsString = this.cleavage.toString();
                break;
            case "Crustal Abundance":
                valueAsString = this.crustalAbundance.toString();
                break;
            case "Economic Value":
                valueAsString = this.economicValue.toString();
                break;
            case "Hardness":
                valueAsString = this.hardness.toString();
                break;
            case "Specific Gravity":
                valueAsString = this.specificGravity.toString();
                break;
            default:
                valueAsString = "Error";
                break;
        }
        return valueAsString;
    }



    //    ***Getters***
    public Hardness getHardness(){return hardness;}
    public Cleavage getCleavage() {return cleavage;}
    public EconomicValue getEconomicValue(){return economicValue;}
    public SpecificGravity getSpecificGravity(){return specificGravity;}
    public CrustalAbundance getCrustalAbundance(){return crustalAbundance;}

}
