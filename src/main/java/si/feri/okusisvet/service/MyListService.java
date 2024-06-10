package si.feri.okusisvet.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.feri.okusisvet.dtomodel.MyListDto;
import si.feri.okusisvet.dtomodel.recipe.RecipeDto;
import si.feri.okusisvet.exceptions.IllegalResourceAccess;
import si.feri.okusisvet.exceptions.ItemNotFoundException;
import si.feri.okusisvet.mappers.RecipeListMapper;
import si.feri.okusisvet.mappers.recipe.RecipeMapper;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.model.RecipeList;
import si.feri.okusisvet.model.RecipeListContent;
import si.feri.okusisvet.repository.mylist.RecipeListContentRepo;
import si.feri.okusisvet.repository.mylist.RecipeListRepo;
import si.feri.okusisvet.repository.recipe.RecipeRepo;
import si.feri.okusisvet.util.SessionUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyListService {
    private final RecipeRepo recipeRepo;
    private final RecipeListRepo recipeListRepo;
    private final RecipeListContentRepo recipeListContentRepo;

    public UUID createNewList(String title, HttpServletRequest req) {
        String userId = SessionUtil.getUserIdStrict(req);

        RecipeList recipeList = new RecipeList();
        recipeList.setTitle(title);
        recipeList.setOwnerId(userId);
        return recipeListRepo.save(recipeList).getId();
    }

    public void updateList(String title, UUID id, HttpServletRequest req) {
        String userId = SessionUtil.getUserIdStrict(req);

        Optional<RecipeList> list = recipeListRepo.findById(id);
        if (list.isEmpty()) {
            throw new ItemNotFoundException("List not found by id");
        }

        if (!list.get().getOwnerId().equals(userId)) {
            throw new ItemNotFoundException("List not found by id");
        }

        list.get().setTitle(title);
        recipeListRepo.save(list.get());
    }

    public List<MyListDto> getUsersLists(HttpServletRequest req) {
        String userId = SessionUtil.getUserIdStrict(req);
        return recipeListRepo.findAllByOwnerId(userId).stream().map(RecipeListMapper.INSTANCE::toMyListDto).toList();
    }

    public void addRecipeToList(UUID recipeId, UUID listId, HttpServletRequest req) {
        String userId = SessionUtil.getUserIdStrict(req);

        Recipe recipe = recipeRepo.findById(recipeId).orElseThrow(()->new ItemNotFoundException("Recipe not found by id"));
        if (!recipe.getState().isShowInPublicList() && !recipe.getOwnerId().equals(userId)) {
            throw new IllegalResourceAccess("Bad request");
        }

        RecipeList recipeList = recipeListRepo.findById(listId).orElseThrow(()->new ItemNotFoundException("List not found by id"));
        if (!recipeList.getOwnerId().equals(userId)) {
            throw new IllegalResourceAccess("Bad request");
        }

        RecipeListContent recipeListContent = new RecipeListContent();
        recipeListContent.setRecipeId(recipeId);
        recipeListContent.setRecipeListId(listId);
        recipeListContentRepo.save(recipeListContent);
    }

    public void removeRecipeFromList(UUID recipeId, UUID listId, HttpServletRequest req) {
        String userId = SessionUtil.getUserIdStrict(req);

        RecipeList recipeList = recipeListRepo.findById(listId).orElseThrow(()->new ItemNotFoundException("List not found by id"));
        if (!recipeList.getOwnerId().equals(userId)) {
            throw new IllegalResourceAccess("Bad request");
        }

        RecipeListContent listContent = recipeListContentRepo.findFirstByRecipeIdAndRecipeListId(recipeId, listId);
        if (listContent == null) {
            return;
        }

        recipeListContentRepo.delete(listContent);
    }

    public List<RecipeDto> getMyListRecipes(UUID listId, HttpServletRequest req) {
        String userId = SessionUtil.getUserIdStrict(req);
        RecipeList recipeList = recipeListRepo.findById(listId).orElseThrow(()->new ItemNotFoundException("List not found by id"));
        if (!recipeList.getOwnerId().equals(userId)) {
           throw new IllegalResourceAccess("Bad request");
        }

        List<UUID> recipeIds = recipeListContentRepo
                .findAllByRecipeListId(listId)
                .stream().map(RecipeListContent::getRecipeId)
                .toList();

        if (recipeIds.isEmpty()) {
            return null;
        }

        return recipeRepo
                .findAllById(recipeIds)
                .stream().map(RecipeMapper.INSTANCE::toDto)
                .toList();
    }

}
