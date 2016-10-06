package Trumps;

/**
 * Created by james on 14/09/2016. Parent class of trump category classes that holds method for
 * parsing the ranges required for Hardness and Specific Gravity classes and toString method for all
 * subclasses.
 */
class TrumpCategory {
  String asStr;

  double parseRange(String rangeString) {
    String rangeStringStripped = rangeString.replaceAll("\\s+", "");
    if (rangeStringStripped.contains("-")) {
      int upperRangeIndex = rangeStringStripped.indexOf('-') + 1;
      return Double.parseDouble(rangeStringStripped.substring(upperRangeIndex));
    } else {
      return Double.parseDouble(rangeStringStripped);
    }
  }

  public String toString() {
    return asStr;
  }
}
