package si.feri.okusisvet.dtomodel;

import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.Unit;

@Getter
@Setter
public class NewIngredientType {
    private String name;
    private Unit defaultUnit;
}
