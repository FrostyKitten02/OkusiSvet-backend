package si.feri.okusisvet.repository.recipe;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import si.feri.okusisvet.enums.RecipeState;
import si.feri.okusisvet.model.QRecipe;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.repository.QuerydslParent;

import java.util.EnumSet;

public class CustomRecipeRepoImpl extends QuerydslParent implements CustomRecipeRepo {
    public CustomRecipeRepoImpl() {
        super(Recipe.class);
    }

    @Override
    public Page<Recipe> findAllPublicByCriteria(String searchStr, Pageable pageable) {
        QRecipe qRecipe = QRecipe.recipe;
        BooleanBuilder restrictions = new BooleanBuilder();

        if (searchStr != null) {
            BooleanBuilder searchStringRestrictions = new BooleanBuilder();
            for (String word : searchStr.trim().split(" ")) {
                searchStringRestrictions.or(qRecipe.title.containsIgnoreCase(word));
            }
            restrictions.and(searchStringRestrictions);
        }


        BooleanBuilder stateEnumRestrictions = new BooleanBuilder();
        for (RecipeState state : RecipeState.getPublicStates()) {
            stateEnumRestrictions.or(qRecipe.state.eq(state));
        }
        restrictions.and(stateEnumRestrictions);


        Querydsl querydsl = getQuerydsl();
        JPAQuery<Recipe> query = querydsl.<Recipe>createQuery()
                .from(qRecipe)
                .where(restrictions);
        querydsl.applySorting(pageable.getSort(), query);
        return getPage(query, pageable);
    }
}
