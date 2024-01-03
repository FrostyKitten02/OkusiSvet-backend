package si.feri.okusisvet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.MyListDto;
import si.feri.okusisvet.model.RecipeList;

@Mapper
public interface RecipeListMapper {
    RecipeListMapper INSTANCE = Mappers.getMapper(RecipeListMapper.class);

    MyListDto toMyListDto(RecipeList recipeList);
}
