package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class IngredientGroupDto {
    private UUID id;
    private String name;
    private Integer position;
    private List<RecipeIngredientDto> ingredients;
}
