package si.feri.okusisvet.dtomodel.recipe;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.RecipeDifficulty;
import si.feri.okusisvet.enums.RecipeType;

import java.util.UUID;

@Getter
@Setter
public class RecipeDto {
    private UUID id;
    private String title;
    private RecipeType type;
    private RecipeDifficulty difficulty;
}
