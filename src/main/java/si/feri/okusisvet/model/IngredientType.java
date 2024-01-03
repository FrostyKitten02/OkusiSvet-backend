package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.Unit;

@Getter
@Setter
@Entity
public class IngredientType extends BaseModel {
    private String name;//TODO: SET THIS TO UNIQUE IN DB, WILL HAVE TO CHANGE UPDATE AND CREATE METHODS FOR RECIPE AND ALSO MAYBE FOR CREATING INGREDIENTS
    @Enumerated(EnumType.STRING)
    private Unit defaultUnit;
}
