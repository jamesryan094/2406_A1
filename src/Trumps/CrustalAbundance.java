package Trumps;

/**
 * Created by james on 14/09/2016.
 */
public class CrustalAbundance extends TrumpCategory{
    private enum CRUSTAL_ABUNDANCE{ULTRATRACE, TRACE, LOW, MODERATE, HIGH, VERY_HIGH, ERROR}
    private CRUSTAL_ABUNDANCE value;

    public CrustalAbundance(String CAValueString){
        asStr = CAValueString;
        value = enumerateCrustalAbundance(CAValueString);
    }

    private static CRUSTAL_ABUNDANCE enumerateCrustalAbundance(String CAValueString) {
        CRUSTAL_ABUNDANCE CAValue;

        switch(CAValueString){
            case "ultratrace":
                CAValue = CRUSTAL_ABUNDANCE.ULTRATRACE;
                break;
            case "trace":
                CAValue = CRUSTAL_ABUNDANCE.TRACE;
                break;
            case "low":
                CAValue = CRUSTAL_ABUNDANCE.LOW;
                break;
            case "moderate":
                CAValue = CRUSTAL_ABUNDANCE.MODERATE;
                break;
            case "high":
                CAValue = CRUSTAL_ABUNDANCE.HIGH;
                break;
            case "very high":
                CAValue = CRUSTAL_ABUNDANCE.VERY_HIGH;
                break;
            default:
                CAValue = CRUSTAL_ABUNDANCE.ERROR;
        }
        return CAValue;
    }


    public CRUSTAL_ABUNDANCE getValue(){
        return value;
    }

    public boolean isGreaterThan(CrustalAbundance otherCrustalAbundance){
        return (getValue().compareTo(otherCrustalAbundance.getValue())) > 0;
    }
}
