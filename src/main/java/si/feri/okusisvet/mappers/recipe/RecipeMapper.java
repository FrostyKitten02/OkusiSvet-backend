package si.feri.okusisvet.mappers.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.recipe.DetailedRecipeDto;
import si.feri.okusisvet.model.Recipe;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
    DetailedRecipeDto toDetailedDto(Recipe recipe);
}
