package si.feri.okusisvet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.feri.okusisvet.dtomodel.IngredientTypeDto;
import si.feri.okusisvet.dtomodel.IngredientTypeResponseDto;
import si.feri.okusisvet.dtomodel.NewIngredientType;
import si.feri.okusisvet.mappers.IngredientTypeMapper;
import si.feri.okusisvet.model.IngredientType;
import si.feri.okusisvet.repository.IngredientTypeRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientTypeService {
    private final IngredientTypeRepo ingredientTypeRepo;
    public IngredientTypeResponseDto getAllIngredientTypes() {
        List<IngredientType> ingredientTypes = ingredientTypeRepo.findAll();
        List<IngredientTypeDto> ingredientTypeDtos = ingredientTypes.stream().map(IngredientTypeMapper.INSTANCE::toDto).toList();


        IngredientTypeResponseDto response = new IngredientTypeResponseDto();
        response.setIngredientTypes(ingredientTypeDtos);
        return response;
    }

    public UUID addIngredientType(NewIngredientType newIngredientType) {
        IngredientType ingredientType = new IngredientType();
        ingredientType.setName(newIngredientType.getName());
        ingredientType.setDefaultUnit(newIngredientType.getDefaultUnit());
        ingredientTypeRepo.save(ingredientType);
        return ingredientType.getId();
    }
}
