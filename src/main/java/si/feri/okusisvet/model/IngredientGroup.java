package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class IngredientGroup extends BaseModel {
    private String name;
    private Integer position;
    private UUID recipeId;
}
