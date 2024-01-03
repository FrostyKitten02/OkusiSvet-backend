package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.RecipeState;

import java.util.List;

@Getter
@Setter
@Entity
public class Recipe extends BaseModel {
    private String ownerId;
    private String title;
    @Enumerated(EnumType.STRING)
    private RecipeState state;
    //TODO: add recipe type: desert, main course, snack, and more
    //TODO: add recipe difficulty: easy, medium, hard
}
