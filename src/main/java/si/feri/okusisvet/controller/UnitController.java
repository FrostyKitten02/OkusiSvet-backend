package si.feri.okusisvet.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.dtomodel.unit.UnitsResponseDto;
import si.feri.okusisvet.enums.UnitType;
import si.feri.okusisvet.service.UnitService;

@RestController
@RequestMapping("units")
@RequiredArgsConstructor
public class UnitController {
    private final UnitService unitService;

    @GetMapping("type/{unitType}")
    public UnitsResponseDto getUnits(@PathVariable("unitType") @NotNull UnitType unitType) {
        return unitService.getUnitsByUnitType(unitType);
    }
}
