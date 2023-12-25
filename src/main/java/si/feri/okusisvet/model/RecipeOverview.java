package si.feri.okusisvet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Getter
@Setter
@Immutable
@Entity
public class RecipeOverview {
    @Id
    private UUID id;
}
