package si.feri.okusisvet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import si.feri.okusisvet.dtomodel.unit.UnitDto;
import si.feri.okusisvet.enums.Unit;

@Mapper
public interface UnitMapper {

    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    @Mapping(target = "nameShort", source = "unitString")
    UnitDto toDto(Unit unit);
}
