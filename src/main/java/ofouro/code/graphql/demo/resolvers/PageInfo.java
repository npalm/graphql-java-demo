package ofouro.code.graphql.demo.resolvers;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PageInfo {

    private long totalElements;
    private long totalPages;
    private long numberOfElements;
    private long pageNumber;
    private long pageSize;

    PageInfo(Page page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.numberOfElements = page.getNumberOfElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

}
