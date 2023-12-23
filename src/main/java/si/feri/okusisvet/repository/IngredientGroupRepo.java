package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.IngredientGroup;

import java.util.UUID;

public interface IngredientGroupRepo extends JpaRepository<IngredientGroup, UUID> {
}
