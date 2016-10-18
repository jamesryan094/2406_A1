package trumps;

/**
 * Take cleavage attribute value as String and assign corresponding Enumeration. Provides
 * functionality for comparing two values of Cleavage Created by james on 14/09/2016.
 */
public class Cleavage extends TrumpCategory {
  private enum CLEAVAGE {
    NONE,
    POOR_NONE,
    POOR1,
    POOR2,
    GOOD1,
    GOOD1_POOR1,
    GOOD2,
    GOOD3,
    PERFECT1,
    PERFECT1_GOOD1,
    PERFECT1_GOOD2,
    PERFECT2_GOOD1,
    PERFECT3,
    PERFECT4,
    PERFECT6,
    ERROR
  }

  private CLEAVAGE value;

  public Cleavage(String cleavageString) {
    asStr = cleavageString;
    value = enumerateCleavage(cleavageString);
  }

  private static CLEAVAGE enumerateCleavage(String cValueString) {
    CLEAVAGE cValue;
    switch (cValueString) {
      case "none":
        cValue = CLEAVAGE.NONE;
        break;
      case "poor/none":
        cValue = CLEAVAGE.POOR_NONE;
        break;
      case "1 poor":
        cValue = CLEAVAGE.POOR1;
        break;
      case "2 poor":
        cValue = CLEAVAGE.POOR2;
        break;
      case "1 good":
        cValue = CLEAVAGE.GOOD1;
        break;
      case "1 good, 1 poor":
        cValue = CLEAVAGE.GOOD1_POOR1;
        break;
      case "2 good":
        cValue = CLEAVAGE.GOOD2;
        break;
      case "3 good":
        cValue = CLEAVAGE.GOOD3;
        break;
      case "1 perfect":
        cValue = CLEAVAGE.PERFECT1;
        break;
      case "1 perfect, 1 good":
        cValue = CLEAVAGE.PERFECT1_GOOD1;
        break;
      case "1 perfect, 2 good":
        cValue = CLEAVAGE.PERFECT1_GOOD2;
        break;
      case "2 perfect, 1 good":
        cValue = CLEAVAGE.PERFECT2_GOOD1;
        break;
      case "3 perfect":
        cValue = CLEAVAGE.PERFECT3;
        break;
      case "4 perfect":
        cValue = CLEAVAGE.PERFECT4;
        break;
      case "6 perfect":
        cValue = CLEAVAGE.PERFECT6;
        break;
      default:
        cValue = CLEAVAGE.ERROR;
    }
    return cValue;
  }

  public CLEAVAGE getValue() {
    return value;
  }

  public boolean isGreaterThan(Cleavage otherCleavage) {
    return (getValue().compareTo(otherCleavage.getValue())) > 0;
  }
}
