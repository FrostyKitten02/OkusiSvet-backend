package si.feri.okusisvet.repository.recipe;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import si.feri.okusisvet.model.QRecipe;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.model.RecipeOverview;
import si.feri.okusisvet.repository.QuerydslParent;

public class CustomRecipeOverviewRepoImpl extends QuerydslParent implements CustomRecipeOverviewRepo {
    public CustomRecipeOverviewRepoImpl() {
        super(RecipeOverview.class);
    }

    @Override
    public Page<RecipeOverview> findAllByCriteria(boolean onlyPublic, String searchStr, Pageable pageable) {
        QRecipe qRecipe = QRecipe.recipe;
        BooleanBuilder restrictions = new BooleanBuilder();

        Querydsl querydsl = getQuerydsl();
        JPAQuery<RecipeOverview> query = querydsl.<RecipeOverview>createQuery()
                .from(qRecipe)
                .where(restrictions);
        querydsl.applySorting(pageable.getSort(), query);
        return getPage(query, pageable);
    }
}
