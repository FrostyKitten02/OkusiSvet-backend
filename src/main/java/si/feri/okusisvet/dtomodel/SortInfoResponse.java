package si.feri.okusisvet.dtomodel;

import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.paging.SortInfo;

import java.util.List;

@Getter
@Setter
public class SortInfoResponse <T extends SortInfo.IField> {
    private boolean ascending = true;
    private List<T> fields;

    public static <T extends SortInfo.IField> SortInfoResponse<T> fromSortInfo(SortInfo<T> sortInfo) {
        SortInfoResponse<T> sortInfoResponse = new SortInfoResponse<>();
        sortInfoResponse.ascending = sortInfo.isAscending();
        sortInfoResponse.fields = sortInfo.getFields();
        return sortInfoResponse;
    }
}
