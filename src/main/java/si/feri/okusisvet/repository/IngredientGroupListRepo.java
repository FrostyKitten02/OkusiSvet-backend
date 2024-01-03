package si.feri.okusisvet.repository;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import si.feri.okusisvet.model.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IngredientGroupListRepo extends JpaRepository<Ingredient, UUID> {
    @SQL("SELECT * FROM ingredient WHERE ingredient_group_id = $ingredient_group_id")
    List<Ingredient> findIngredientGroupListByIngredientGroupId(@Param("ingredient_group_id") UUID ingredientGroupId);
}
