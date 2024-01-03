package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.feri.okusisvet.model.IngredientType;

import java.util.UUID;

@Repository
public interface IngredientTypeRepo extends JpaRepository<IngredientType, UUID> {
}
