package si.feri.okusisvet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.IngredientTypeDto;
import si.feri.okusisvet.dtomodel.unit.UnitDto;
import si.feri.okusisvet.enums.Unit;
import si.feri.okusisvet.model.IngredientType;

@Mapper
public interface IngredientTypeMapper {
    IngredientTypeMapper INSTANCE = Mappers.getMapper(IngredientTypeMapper.class);

    @Mapping(target = "defaultUnit", source = "defaultUnit", qualifiedByName = "unitToUnitDto")
    IngredientTypeDto toDto(IngredientType ingredientType);

    @Named("unitToUnitDto")
    default UnitDto toUnitDto(Unit unit) {
        return UnitMapper.INSTANCE.toDto(unit);
    }
}
