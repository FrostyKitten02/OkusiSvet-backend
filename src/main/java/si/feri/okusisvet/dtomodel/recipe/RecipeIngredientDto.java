package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.Unit;

import java.util.UUID;

@Getter
@Setter
public class RecipeIngredientDto {
    private UUID id;
    private String name;
    private Integer amount;
    private Unit unit;
}
