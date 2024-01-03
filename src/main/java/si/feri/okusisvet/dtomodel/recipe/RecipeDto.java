package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RecipeDto {
    private UUID id;
    private String title;
}
