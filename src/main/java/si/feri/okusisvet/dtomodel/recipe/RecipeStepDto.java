package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RecipeStepDto {
    private UUID id;
    private Integer stepNumber;
    private String title;
    private String instructions;
}
