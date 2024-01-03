package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.IngredientType;

import java.util.UUID;

public interface IngredientTypeRepo extends JpaRepository<IngredientType, UUID> {
}
