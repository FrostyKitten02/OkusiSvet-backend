package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.Unit;

import java.util.UUID;


@Getter
@Setter
@Entity
public class IngredientGroupList extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "ingredient_group_id", nullable = false)
    private IngredientGroup ingredientGroup;
    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private Unit unit;
}
