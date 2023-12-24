package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.IngredientGroup;
import si.feri.okusisvet.model.IngredientGroupList;

import java.util.List;
import java.util.UUID;

public interface IngredientGroupRepo extends JpaRepository<IngredientGroup, UUID> {
    List<IngredientGroup> findIngredientGroupByRecipeId(UUID recipeId);
}
