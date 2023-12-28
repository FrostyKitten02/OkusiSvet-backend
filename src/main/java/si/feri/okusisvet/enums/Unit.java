package si.feri.okusisvet.enums;

import lombok.Getter;
import si.feri.okusisvet.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

public enum Unit {
    GRAMS("g", UnitType.MASS),
    DECAGRAMS("dag", UnitType.MASS),
    KILOGRAMS("kg", UnitType.MASS),


    //TODO ADD AMERICAN UNITS!!! MAYBE???, user boolean and dont change unit type? which is better??
    MILLILITERS("ml", UnitType.VOLUME),
    DECILITERS("dl", UnitType.VOLUME),
    LITERS("l", UnitType.VOLUME),

    //TODO currently only in slovenian, change to enable translations
    PIECE("kos", UnitType.PIECE);


    private static final List<Unit> METRIC_MASS_UNITS = new ArrayList<>(3);

    private static final List<Unit> METRIC_VOLUME_UNITS = new ArrayList<>(3);

    private static final List<Unit> OTHER = new ArrayList<>(1);

    static {
        for (Unit unit : Unit.values()) {
            if (unit.getUnitType()==UnitType.MASS) {
                METRIC_MASS_UNITS.add(unit);
            } else if (unit.getUnitType()==UnitType.VOLUME) {
                METRIC_VOLUME_UNITS.add(unit);
            } else {
                OTHER.add(unit);
            }
        }
    }

    public static List<Unit> getUnitsByType(UnitType unitType) {
        return switch (unitType) {
            case MASS -> METRIC_MASS_UNITS;
            case VOLUME -> METRIC_VOLUME_UNITS;
            case PIECE -> OTHER;
            default -> throw new ItemNotFoundException("Unit type not found");
        };
    }

    @Getter
    private String unitString;
    @Getter
    private UnitType unitType;

    //for mappers
    public String getName() {
        return this.name();
    }
    Unit (String unitString, UnitType unitType) {
        this.unitString = unitString;
        this.unitType = unitType;
    }

}
