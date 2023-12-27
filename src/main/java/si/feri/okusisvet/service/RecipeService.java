package si.feri.okusisvet.service;

import com.google.api.client.util.Value;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import si.feri.okusisvet.dtomodel.PageInfoRequest;
import si.feri.okusisvet.dtomodel.recipe.CreateRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.DetailedRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.IngredientGroupDto;
import si.feri.okusisvet.dtomodel.recipe.RecipeIngredientDto;
import si.feri.okusisvet.dtomodel.recipe.RecipeSearchParams;
import si.feri.okusisvet.dtomodel.recipe.RecipeSortInfoRequest;
import si.feri.okusisvet.enums.RecipeState;
import si.feri.okusisvet.exceptions.BadRequestException;
import si.feri.okusisvet.exceptions.IllegalResourceAccess;
import si.feri.okusisvet.exceptions.ItemNotFoundException;
import si.feri.okusisvet.mappers.recipe.IngredientMapper;
import si.feri.okusisvet.mappers.recipe.IngredientGroupMapper;
import si.feri.okusisvet.mappers.recipe.RecipeMapper;
import si.feri.okusisvet.model.Ingredient;
import si.feri.okusisvet.model.IngredientGroup;
import si.feri.okusisvet.model.IngredientType;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.paging.PageInfo;
import si.feri.okusisvet.paging.RecipeSortInfo;
import si.feri.okusisvet.paging.SortInfo;
import si.feri.okusisvet.repository.IngredientGroupListRepo;
import si.feri.okusisvet.repository.IngredientGroupRepo;
import si.feri.okusisvet.repository.IngredientTypeRepo;
import si.feri.okusisvet.repository.recipe.RecipeRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepo recipeRepo;
    private final IngredientTypeRepo ingredientTypeRepo;
    private final IngredientGroupRepo ingredientGroupRepo;
    private final IngredientGroupListRepo ingredientGroupListRepo;

    private static final String TEST_USER_ID = "TEST";

    public Page<Recipe> searchRecipes(@NotNull PageInfoRequest pageInfoRequest, RecipeSortInfoRequest sortInfoRequest, RecipeSearchParams searchParams) {

        SortInfo<?> sort;
        if (sortInfoRequest != null) {
            sort = sortInfoRequest.toSortInfo();
        } else {
            sort = new RecipeSortInfo();
        }

        String searchStr;
        if (searchParams == null) {
            searchStr = null;
        } else {
            searchStr = searchParams.getSearchStr();
        }

        return recipeRepo.findAllPublicByCriteria(searchStr, PageInfo.toPageRequest(pageInfoRequest.toPageInfo(), sort));
    }

    //TODO use one querry!
    public DetailedRecipeDto getDetailedRecipe(UUID recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).orElseThrow(() -> new ItemNotFoundException("Recipe with id: " + recipeId + " not found!"));
        if (!recipe.getOwnerId().equals(TEST_USER_ID) && !recipe.getState().isShowInPublicList()) {
            throw new IllegalResourceAccess("Recipe with id: " + recipeId + " is not public!");
        }

        List<IngredientGroup> ingredientGroups = ingredientGroupRepo.findIngredientGroupByRecipeId(recipeId);
        List<IngredientGroupDto> ingredientGroupDtos = ingredientGroups.stream().map(ig -> {
            IngredientGroupDto ingredientGroupDto = IngredientGroupMapper.INSTANCE.toDto(ig);
            List<Ingredient> ingredients = ingredientGroupListRepo.findIngredientGroupListByIngredientGroupId(ig.getId());
            List<RecipeIngredientDto> recipeIngredientDtos = ingredients.stream().map(IngredientMapper.INSTANCE::toDto).toList();
            ingredientGroupDto.setIngredients(recipeIngredientDtos);
            return ingredientGroupDto;
        }).toList();

        DetailedRecipeDto detailedRecipeDto = RecipeMapper.INSTANCE.toDetailedDto(recipe);
        detailedRecipeDto.setIngredientGroups(ingredientGroupDtos);
        return detailedRecipeDto;
    }


    @Transactional
    public UUID addRecipe(CreateRecipeDto newRecipe) {
        if (newRecipe.getTitle() == null) {
            throw new BadRequestException("Missing recipe title!");
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(newRecipe.getTitle());
        recipe.setOwnerId("TEST");//TODO set to logged in user, after implementing firebase integration
        if (newRecipe.getPublish() != null && newRecipe.getPublish()) {
            recipe.setState(RecipeState.PUBLIC_PUBLISHED);
        } else {
            recipe.setState(RecipeState.PRIVATE_DRAFT);
        }

        Recipe savedRecipe = recipeRepo.save(recipe);
        List<Ingredient> ingredients = validateCreateRecipeAndCreateIngredientGroups(newRecipe, savedRecipe.getId());
        ingredientGroupListRepo.saveAll(ingredients);
        return savedRecipe.getId();
    }


    private List<Ingredient> validateCreateRecipeAndCreateIngredientGroups(CreateRecipeDto newRecipe, UUID recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        HashMap<String, IngredientType> newlySaved = new HashMap<>();

        newRecipe.getIngredientGroups().forEach(group -> {
            if (group.position() == null || group.position() <= 0) {
                throw new BadRequestException("Missing ingredient group position!");
            }

            if (group.ingredients() == null || group.ingredients().isEmpty()) {
                throw new BadRequestException("Missing ingredient group ingredients!");
            }

//            if (g.name() == null || g.name().isEmpty()) {
//                throw new BadRequestException("Missing ingredient group name!");
//            }

            final IngredientGroup ingredientGroup = new IngredientGroup();
            ingredientGroup.setName(group.name());
            ingredientGroup.setPosition(group.position());
            ingredientGroup.setRecipeId(recipeId);
            ingredientGroupRepo.save(ingredientGroup);

            group.ingredients().forEach(ingredient -> {
                if (ingredient.amount() == null || ingredient.amount() <= 0) {
                    throw new BadRequestException("Missing ingredient amount! for ingredient: " + ingredient.name());
                }

                if (ingredient.id() == null && ingredient.name() == null) {
                    throw new BadRequestException("Missing ingredient id or name!");
                }

                if (ingredient.unit() == null) {
                    throw new BadRequestException("Missing ingredient unit! for ingredient: " + ingredient.name());
                }

                //maybe allow this and use id??
                if (ingredient.id() != null && ingredient.name() != null) {
                    throw new BadRequestException("Ingredient can have only id or name!");
                }


                final Ingredient ingredientGroupList = new Ingredient();
                ingredientGroupList.setIngredientGroup(ingredientGroup);
                ingredientGroupList.setAmount(ingredient.amount());
                ingredientGroupList.setUnit(ingredient.unit());
                if (ingredient.id() != null) {
                    IngredientType ingredientTypeDb = ingredientTypeRepo.findById(ingredient.id()).orElseThrow(() -> new ItemNotFoundException("Ingredient with id: " + ingredient.id() + " not found!"));
                    ingredientGroupList.setIngredientType(ingredientTypeDb);

                } else {
                    IngredientType fromHashMap = newlySaved.get(ingredient.name());
                    if (fromHashMap != null) {
                        ingredientGroupList.setIngredientType(fromHashMap);
                    } else {
                        IngredientType newIngredientType = new IngredientType();
                        newIngredientType.setName(ingredient.name());
                        newIngredientType.setDefaultUnit(ingredient.unit());

                        IngredientType saved = ingredientTypeRepo.save(newIngredientType);
                        ingredientGroupList.setIngredientType(saved);
                        newlySaved.put(saved.getName(), saved);
                    }
                }

                ingredients.add(ingredientGroupList);
            });


        });

        return ingredients;
    }

}
