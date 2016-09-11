/**
 * Created by james on 10/09/2016.
 */
public class Enum_play {
    final static int TRIVIAL = 0;
    final static int LOW = 1;
    final static int MODERATE = 2;
    final static int HIGH = 3;
    final static int VERY_HIGH = 4;
    final static int IM_RICH = 5;
    enum ECONOMIC_VALUE{TRIVIAL, LOW, MODERATE, HIGH, VERY_HIGH, IM_RICH};

    public static int enumerateEconomicValue(String eValueString){
        ECONOMIC_VALUE eValue;

        switch(eValueString){
            case "trival":
                eValue = ECONOMIC_VALUE.TRIVIAL;

        }
        return eValue;
    }

    public static void compare() {
        


    }
}
