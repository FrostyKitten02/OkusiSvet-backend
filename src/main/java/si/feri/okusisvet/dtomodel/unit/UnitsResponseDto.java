package si.feri.okusisvet.dtomodel.unit;

import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.UnitType;

import java.util.List;

@Getter
@Setter
public class UnitsResponseDto {
    private List<UnitDto> units;

}
