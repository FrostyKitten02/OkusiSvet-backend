package si.feri.okusisvet.enums;

public enum Unit {
    GRAMS("g", UnitType.MASS),
    DECAGRAMS("dag", UnitType.MASS),
    KILOGRAMS("kg", UnitType.MASS),


    //TODO ADD AMERICAN UNITS!!! MAYBE???
    MILLILITERS("ml", UnitType.VOLUME),
    DECILITERS("dl", UnitType.VOLUME),
    LITERS("l", UnitType.VOLUME),

    //TODO currently only in slovenian, change to enable translations
    PIECE("kos", UnitType.PIECE);
    Unit (String unitString, UnitType unitType) {}
}
