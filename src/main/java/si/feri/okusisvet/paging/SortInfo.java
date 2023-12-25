package si.feri.okusisvet.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class SortInfo <T extends SortInfo.IField> {
    private boolean ascending;
    private List<T> fields;
    private T defaultField;
    public Sort getSort() {
        if (fields == null || fields.isEmpty()) {
            return Sort.by(defaultField.getColumnName());
        }

        Sort sorting = Sort.by(fields.stream().map(IField::getColumnName).toArray(String[]::new));
        if (ascending) {
            return sorting.ascending();
        }

        return sorting.descending();
    }

    public interface IField {
        String getColumnName();
    }
}