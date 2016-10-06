package Trumps;

/**
 * Take Economic Value attribute value as String and assign corresponding Enumeration. Provides
 * functionality for comparing two values of Economic Value. Created by james on 10/09/2016.
 */
public class EconomicValue extends TrumpCategory {
  private enum ECONOMIC_VALUE {
    TRIVIAL,
    LOW,
    MODERATE,
    HIGH,
    VERY_HIGH,
    IM_RICH
  }

  private ECONOMIC_VALUE value;
  //    private String asStr;

  public EconomicValue(String eValueString) {
    asStr = eValueString;
    value = enumerateEconomicValue(eValueString);
  }

  private static ECONOMIC_VALUE enumerateEconomicValue(String eValueString) {
    ECONOMIC_VALUE eValue;

    switch (eValueString) {
      case "trivial":
        eValue = ECONOMIC_VALUE.TRIVIAL;
        break;
      case "low":
        eValue = ECONOMIC_VALUE.LOW;
        break;
      case "moderate":
        eValue = ECONOMIC_VALUE.MODERATE;
        break;
      case "high":
        eValue = ECONOMIC_VALUE.HIGH;
        break;
      case "very high":
        eValue = ECONOMIC_VALUE.VERY_HIGH;
        break;
      case "I'm rich!":
        eValue = ECONOMIC_VALUE.IM_RICH;
        break;
      default:
        eValue = ECONOMIC_VALUE.TRIVIAL;
    }
    return eValue;
  }

  public ECONOMIC_VALUE getValue() {
    return value;
  }

  public boolean isGreaterThan(EconomicValue otherEV) {
    return (getValue().compareTo(otherEV.getValue())) > 0;
  }
}
