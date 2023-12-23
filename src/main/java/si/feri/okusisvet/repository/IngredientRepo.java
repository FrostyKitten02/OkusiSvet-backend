package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.Ingredient;

import java.util.UUID;

public interface IngredientRepo extends JpaRepository<Ingredient, UUID> {
}
