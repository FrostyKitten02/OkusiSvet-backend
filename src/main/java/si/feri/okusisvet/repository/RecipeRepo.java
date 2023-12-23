package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.Recipe;

import java.util.UUID;

public interface RecipeRepo extends JpaRepository<Recipe, UUID> {
}