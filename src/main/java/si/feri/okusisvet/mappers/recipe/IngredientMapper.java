package si.feri.okusisvet.mappers.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.recipe.RecipeIngredientDto;
import si.feri.okusisvet.model.Ingredient;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
    @Mapping(source = "ingredientType.name", target = "name")
    RecipeIngredientDto toDto(Ingredient ingredient);
}
