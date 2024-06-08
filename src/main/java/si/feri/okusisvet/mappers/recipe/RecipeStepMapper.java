package si.feri.okusisvet.mappers.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.recipe.RecipeStepDto;
import si.feri.okusisvet.model.RecipeStep;

@Mapper
public interface RecipeStepMapper {
    RecipeStepMapper INSTANCE = Mappers.getMapper(RecipeStepMapper.class);
    RecipeStepDto toDto(RecipeStep recipeStep);
}
