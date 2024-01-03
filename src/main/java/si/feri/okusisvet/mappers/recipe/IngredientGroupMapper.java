package si.feri.okusisvet.mappers.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.recipe.IngredientGroupDto;
import si.feri.okusisvet.model.IngredientGroup;

@Mapper
public interface IngredientGroupMapper {
    IngredientGroupMapper INSTANCE = Mappers.getMapper(IngredientGroupMapper.class);
    IngredientGroupDto toDto(IngredientGroup ingredientGroup);
}
