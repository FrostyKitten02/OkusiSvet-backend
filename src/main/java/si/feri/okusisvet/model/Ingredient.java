package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.Unit;


@Getter
@Setter
@Entity
public class Ingredient extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "ingredient_group_id", nullable = false)
    private IngredientGroup ingredientGroup;
    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private IngredientType ingredientType;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private Unit unit;
}
