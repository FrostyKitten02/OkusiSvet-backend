package si.feri.okusisvet.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.feri.okusisvet.dtomodel.recipe.CreateRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.DetailedRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.IngredientGroupDto;
import si.feri.okusisvet.dtomodel.recipe.RecipeIngredientDto;
import si.feri.okusisvet.enums.RecipeState;
import si.feri.okusisvet.exceptions.BadRequestException;
import si.feri.okusisvet.exceptions.ItemNotFoundException;
import si.feri.okusisvet.mappers.recipe.IngredientGroupListMapper;
import si.feri.okusisvet.mappers.recipe.IngredientGroupMapper;
import si.feri.okusisvet.mappers.recipe.RecipeMapper;
import si.feri.okusisvet.model.Ingredient;
import si.feri.okusisvet.model.IngredientGroup;
import si.feri.okusisvet.model.IngredientGroupList;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.repository.IngredientGroupListRepo;
import si.feri.okusisvet.repository.IngredientGroupRepo;
import si.feri.okusisvet.repository.IngredientRepo;
import si.feri.okusisvet.repository.RecipeRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepo recipeRepo;
    private final IngredientRepo ingredientRepo;
    private final IngredientGroupRepo ingredientGroupRepo;
    private final IngredientGroupListRepo ingredientGroupListRepo;


    //TODO use one querry!
    public DetailedRecipeDto getDetailedRecipe(UUID recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).orElseThrow(() -> new ItemNotFoundException("Recipe with id: " + recipeId + " not found!"));
        List<IngredientGroup> ingredientGroups = ingredientGroupRepo.findIngredientGroupByRecipeId(recipeId);
        List<IngredientGroupDto> ingredientGroupDtos = ingredientGroups.stream().map(ig -> {
            IngredientGroupDto ingredientGroupDto = IngredientGroupMapper.INSTANCE.toDto(ig);
            List<IngredientGroupList> ingredientGroupLists = ingredientGroupListRepo.findIngredientGroupListByIngredientGroupId(ig.getId());
            List<RecipeIngredientDto> recipeIngredientDtos = ingredientGroupLists.stream().map(IngredientGroupListMapper.INSTANCE::toDto).toList();
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
        List<IngredientGroupList> ingredientGroupLists = validateCreateRecipeAndCreateIngredientGroups(newRecipe, savedRecipe.getId());
        ingredientGroupListRepo.saveAll(ingredientGroupLists);
        return savedRecipe.getId();
    }


    private List<IngredientGroupList> validateCreateRecipeAndCreateIngredientGroups(CreateRecipeDto newRecipe, UUID recipeId) {
        List<IngredientGroupList> ingredientGroupLists = new ArrayList<>();
        HashMap<String, Ingredient> newlySaved = new HashMap<>();

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


                final IngredientGroupList ingredientGroupList = new IngredientGroupList();
                ingredientGroupList.setIngredientGroup(ingredientGroup);
                ingredientGroupList.setAmount(ingredient.amount());
                ingredientGroupList.setUnit(ingredient.unit());
                if (ingredient.id() != null) {
                    Ingredient ingredientDb = ingredientRepo.findById(ingredient.id()).orElseThrow(() -> new ItemNotFoundException("Ingredient with id: " + ingredient.id() + " not found!"));
                    ingredientGroupList.setIngredient(ingredientDb);

                } else {
                    Ingredient fromHashMap = newlySaved.get(ingredient.name());
                    if (fromHashMap != null) {
                        ingredientGroupList.setIngredient(fromHashMap);
                    } else {
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(ingredient.name());
                        newIngredient.setDefaultUnit(ingredient.unit());

                        Ingredient saved = ingredientRepo.save(newIngredient);
                        ingredientGroupList.setIngredient(saved);
                        newlySaved.put(saved.getName(), saved);
                    }
                }

                ingredientGroupLists.add(ingredientGroupList);
            });


        });

        return ingredientGroupLists;
    }

}
