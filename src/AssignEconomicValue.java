/**
 * Created by james on 10/09/2016.
 */

public class AssignEconomicValue {
    public enum ECONOMIC_VALUE {TRIVIAL, LOW, MODERATE, HIGH, VERY_HIGH, IM_RICH}

    public static ECONOMIC_VALUE enumerateEconomicValue(String eValueString) {
        ECONOMIC_VALUE eValue;

        switch (eValueString) {
            case "trival":
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
}

//    public static void compare() {
//
//Card c1= new card
//        c2 =
//                if(c1.enconvalue <= c2.)

//    }