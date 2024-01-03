package si.feri.okusisvet.dtomodel;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.paging.PageInfo;

@Getter
@Setter
public class PageInfoRequest {
    @Min(1)
    private Integer elementsPerPage;
    @Min(1)
    private Integer pageNumber;

    public PageInfo toPageInfo() {
        return new PageInfo(elementsPerPage, null, pageNumber);
    }
}
