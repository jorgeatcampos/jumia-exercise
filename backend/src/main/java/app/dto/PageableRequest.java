package app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Generic pageable request that allows number of records and page index specification.
 */
@AllArgsConstructor
@Getter
@Setter
abstract class PageableRequest {
    private static final int NUMBER_OF_RECORDS_DEFAULT = 10;
    private static final int PAGE_INDEX_DEFAULT = 0;

    /**
     * The number of records that should be returned per page
     */
    private int numberOfRecords;

    /**
     * The number of the page
     */
    private int pageIndex;

    public PageableRequest() {
        this.numberOfRecords = NUMBER_OF_RECORDS_DEFAULT;
        this.pageIndex = PAGE_INDEX_DEFAULT;
    }
}
