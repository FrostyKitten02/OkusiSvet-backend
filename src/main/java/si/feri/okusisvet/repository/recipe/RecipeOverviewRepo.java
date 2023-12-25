package si.feri.okusisvet.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import si.feri.okusisvet.model.RecipeOverview;

import java.util.UUID;

public interface RecipeOverviewRepo extends JpaRepository<RecipeOverview, UUID> {
}
