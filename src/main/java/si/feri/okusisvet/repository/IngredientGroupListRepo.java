package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.IngredientGroupList;

import java.util.UUID;

public interface IngredientGroupListRepo extends JpaRepository<IngredientGroupList, UUID> {
}
