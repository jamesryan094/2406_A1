package Cards;

import java.util.ArrayList;
import Trumps.*;

/**
 * One of two types of playable cards in the game. Groups information on a particular mineral and
 * store playable values in a comparable form. Created by james on 6/09/2016.
 */
public class MineralCard extends Card {

  private String chemistry, classification, crystalSystem, occurrence;

  //  Trump Categories
  private Cleavage cleavage;
  private CrustalAbundance crustalAbundance;
  private EconomicValue economicValue;
  private Hardness hardness;
  private SpecificGravity specificGravity;

  /**
   * Takes information for every attribute from the passed in ArrayList, stores the non playable
   * information as strings, Passes playable values to respective class constructors to store as
   * Trump objects.
   *
   * @see Trumps
   * @param cardAttributes a String cast ArrayList holding all of a mineral card's information
   */
  public MineralCard(ArrayList<String> cardAttributes) {
    super(cardAttributes);
    chemistry = cardAttributes.get(4);
    classification = cardAttributes.get(5);
    crystalSystem = cardAttributes.get(6);
    occurrence = cardAttributes.get(7);
    hardness = new Hardness(cardAttributes.get(8));
    specificGravity = new SpecificGravity(cardAttributes.get(9));
    cleavage = new Cleavage(cardAttributes.get(10));
    crustalAbundance = new CrustalAbundance(cardAttributes.get(11));
    economicValue = new EconomicValue(cardAttributes.get(12));
  }

  /**
   * Determines the current trump category in play and compares card's own value of that category,
   * with the current card in play.
   *
   * @param otherCard The current card in play.
   * @param currentTrumpCategory The current trump category in play.
   * @return true if this card has a higher than the current card in play, or true if the last card
   *     played was a trump
   */
  public boolean canPlayOn(Card otherCard, String currentTrumpCategory) {
    if (otherCard.getCardType().equals("trump")) {
      return true;
    }
    MineralCard otherMCard = (MineralCard) otherCard;
    switch (currentTrumpCategory) {
      case "Cleavage":
        Cleavage myCleavage = getCleavage();
        Cleavage otherCleavage = otherMCard.getCleavage();
        if (myCleavage.isGreaterThan(otherCleavage)) {
          return true;
        }
        break;
      case "Crustal Abundance":
        CrustalAbundance myCA = getCrustalAbundance();
        CrustalAbundance otherCA = otherMCard.getCrustalAbundance();
        if (myCA.isGreaterThan(otherCA)) {
          return true;
        }
        break;
      case "Economic Value":
        EconomicValue myEV = getEconomicValue();
        EconomicValue otherEV = otherMCard.getEconomicValue();
        if (myEV.isGreaterThan(otherEV)) {
          return true;
        }
        break;
      case "Hardness":
        Hardness myHardness = getHardness();
        Hardness otherHardness = otherMCard.getHardness();
        if (myHardness.isGreaterThan(otherHardness)) {
          return true;
        }
        break;
      case "Specific Gravity":
        SpecificGravity mySG = getSpecificGravity();
        SpecificGravity otherSG = otherMCard.getSpecificGravity();
        if (mySG.isGreaterThan(otherSG)) {
          return true;
        }
        break;
      default:
        System.out.println("Error");
        return false;
    }
    return false;
  }

  public void printAttributes() {
    super.printAttributes();
    System.out.println(
        "Chemistry: "
            + chemistry
            + "\nClassification: "
            + classification
            + "\nCrystal System: "
            + crystalSystem
            + "\nOccurrence: "
            + occurrence
            + "\nHardness: "
            + hardness.toString()
            + "\nSpecific Gravity: "
            + specificGravity.toString()
            + "\nCleavage: "
            + cleavage
            + "\nCrustal Abundance: "
            + crustalAbundance
            + "\nEconomic Value: "
            + economicValue.toString()
            + "\n--------------------");
  }

  //    ***Getters***
  private Hardness getHardness() {
    return hardness;
  }

  private Cleavage getCleavage() {
    return cleavage;
  }

  private EconomicValue getEconomicValue() {
    return economicValue;
  }

  private SpecificGravity getSpecificGravity() {
    return specificGravity;
  }

  private CrustalAbundance getCrustalAbundance() {
    return crustalAbundance;
  }

  public String getCurrentTrumpValueAsString(String currentTrumpCategory) {
    String valueAsString;
    switch (currentTrumpCategory) {
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
}
