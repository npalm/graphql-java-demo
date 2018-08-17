package ofouro.code.graphql.demo.resolvers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
abstract class PageableResponse {

    PageInfo pageInfo;

    PageableResponse(Page page) {
        this.pageInfo = new PageInfo(page);
    }

}
