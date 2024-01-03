package si.feri.okusisvet.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.feri.okusisvet.dtomodel.unit.UnitDto;
import si.feri.okusisvet.dtomodel.unit.UnitsResponseDto;
import si.feri.okusisvet.enums.Unit;
import si.feri.okusisvet.enums.UnitType;
import si.feri.okusisvet.mappers.UnitMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {
    public UnitsResponseDto getUnitsByUnitType(@NotNull UnitType unitType) {
        List<Unit> units = Unit.getUnitsByType(unitType);
        List<UnitDto> unitDtos = units.stream().map(UnitMapper.INSTANCE::toDto).toList();
        UnitsResponseDto response = new UnitsResponseDto();
        response.setUnits(unitDtos);
        return response;
    }
}
