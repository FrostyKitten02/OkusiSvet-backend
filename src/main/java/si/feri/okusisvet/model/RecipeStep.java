package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class RecipeStep extends BaseModel {
    private Integer stepNumber;
    private String title;
    private String instructions;
    private UUID recipeId;
}
