package si.feri.okusisvet.repository;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import si.feri.okusisvet.model.IngredientGroupList;

import java.util.List;
import java.util.UUID;

public interface IngredientGroupListRepo extends JpaRepository<IngredientGroupList, UUID> {
    @SQL("SELECT * FROM ingredient_group_list WHERE ingredient_group_id = $ingredient_group_id")
    List<IngredientGroupList> findIngredientGroupListByIngredientGroupId(@Param("ingredient_group_id") UUID ingredientGroupId);
}
