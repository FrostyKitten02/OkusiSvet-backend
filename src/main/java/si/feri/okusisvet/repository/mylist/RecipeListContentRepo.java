package si.feri.okusisvet.repository.mylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.feri.okusisvet.model.RecipeListContent;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeListContentRepo extends JpaRepository<RecipeListContent, UUID> {
    List<RecipeListContent> findAllByRecipeListId(UUID recipeListId);
    RecipeListContent findFirstByRecipeIdAndRecipeListId(UUID recipeId, UUID recipeListId);
}
