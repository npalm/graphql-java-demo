package ofouro.code.graphql.demo.resolvers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPage {

    private int page;
    private int size;

    static PageRequest convert(InputPage inputPage) {
        return inputPage != null ? PageRequest.of(inputPage.page, inputPage.size, Sort.Direction.DESC, "createdOn") : PageRequest.of(0, 20, Sort.Direction.DESC, "createdOn");
    }
}
