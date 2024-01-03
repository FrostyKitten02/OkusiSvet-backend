package si.feri.okusisvet.paging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.domain.Page;
import si.feri.okusisvet.model.Recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class RecipeSortInfo extends SortInfo<RecipeSortInfo.Field> {
    @Getter
    @JsonIgnore
    private static final Map<String, Field> fieldMap = new HashMap<>();

    static {
        Arrays.stream(Field.values()).sorted().forEach(field -> {
            fieldMap.put(field.getColumnName(), field);
        });
    }

    public RecipeSortInfo(List<RecipeSortInfo.Field> fields, boolean ascending) {
        super(ascending, fields, Field.TITLE);
    }

    public RecipeSortInfo() {
        super(true, null, Field.TITLE);
    }

    public static RecipeSortInfo fromPage(Page<Recipe> page) {
        boolean ascending = true;
        if (!page.getSort().toList().isEmpty()) {
            ascending = page.getSort().toList().getFirst().getDirection().isAscending();
        } else {
            return null;
        }

        List<RecipeSortInfo.Field> sortFields = page.getSort().get().map(order -> {
            return fieldMap.get(order.getProperty());
        }).filter(Objects::nonNull).toList();


        if (sortFields.isEmpty()) {
            return new RecipeSortInfo(null, ascending);
        }

        return new RecipeSortInfo(sortFields, ascending);
    }

    public enum Field implements IField {
        TITLE("title");
        @Getter
        private final String columnName;
        Field(String columnName) {
            this.columnName = columnName;
        }
    }

}
