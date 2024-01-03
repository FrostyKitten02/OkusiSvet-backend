package si.feri.okusisvet.repository.mylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.feri.okusisvet.model.RecipeList;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeListRepo extends JpaRepository<RecipeList, UUID> {
    List<RecipeList> findAllByOwnerId(String ownerId);
}
