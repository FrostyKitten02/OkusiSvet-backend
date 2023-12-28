package si.feri.okusisvet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.feri.okusisvet.dtomodel.IngredientTypeDto;
import si.feri.okusisvet.dtomodel.IngredientTypeResponseDto;
import si.feri.okusisvet.mappers.IngredientTypeMapper;
import si.feri.okusisvet.model.IngredientType;
import si.feri.okusisvet.repository.IngredientTypeRepo;

import java.util.List;

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
}
