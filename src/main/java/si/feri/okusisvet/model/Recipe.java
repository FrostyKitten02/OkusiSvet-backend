package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.RecipeDifficulty;
import si.feri.okusisvet.enums.RecipeState;
import si.feri.okusisvet.enums.RecipeType;

import java.util.List;

@Getter
@Setter
@Entity
public class Recipe extends BaseModel {
    private String ownerId;
    private String title;
    private String comment;
    @Enumerated(EnumType.STRING)
    private RecipeState state;
    @Enumerated(EnumType.STRING)
    private RecipeType type;
    @Enumerated(EnumType.STRING)
    private RecipeDifficulty difficulty;
}
