package si.feri.okusisvet.mappers.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.recipe.RecipeIngredientDto;
import si.feri.okusisvet.model.IngredientGroupList;

@Mapper
public interface IngredientGroupListMapper {
    IngredientGroupListMapper INSTANCE = Mappers.getMapper(IngredientGroupListMapper.class);
    @Mapping(source = "ingredient.name", target = "name")
    RecipeIngredientDto toDto(IngredientGroupList ingredientGroupList);
}
