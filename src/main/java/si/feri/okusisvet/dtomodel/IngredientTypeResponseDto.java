package si.feri.okusisvet.dtomodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IngredientTypeResponseDto {
    private List<IngredientTypeDto> ingredientTypes;
}
