package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecipeList extends BaseModel {
    private String title;
    private String ownerId;
}
