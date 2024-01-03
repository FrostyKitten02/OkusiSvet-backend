package si.feri.okusisvet.dtomodel.recipe;

import si.feri.okusisvet.dtomodel.SortInfoRequest;
import si.feri.okusisvet.paging.RecipeSortInfo;

import java.util.ArrayList;
import java.util.List;

public class RecipeSortInfoRequest extends SortInfoRequest<RecipeSortInfo.Field> {
    public RecipeSortInfoRequest() {
        super(true, null, new RecipeSortInfo());
    }
    public RecipeSortInfoRequest(boolean ascending, List<RecipeSortInfo.Field> fields) {
        super(ascending, fields, new RecipeSortInfo(fields, ascending));
    }
}
