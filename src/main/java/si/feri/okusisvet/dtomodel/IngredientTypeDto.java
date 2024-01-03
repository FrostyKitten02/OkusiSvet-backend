package si.feri.okusisvet.dtomodel;

import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.dtomodel.unit.UnitDto;

import java.util.UUID;

@Getter
@Setter
public class IngredientTypeDto {
    private UUID id;
    private String name;
    private UnitDto defaultUnit;
}
