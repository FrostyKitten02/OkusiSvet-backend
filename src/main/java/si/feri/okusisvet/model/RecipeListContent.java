package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
public class RecipeListContent extends BaseModel {
    private UUID recipeId;
    private UUID recipeListId;
}
