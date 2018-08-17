package ofouro.code.graphql.demo.resolvers;

import lombok.Data;
import ofouro.code.graphql.demo.model.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CommentPageableResponse extends PageableResponse {

    private List<Comment> content;

    public CommentPageableResponse(Page<Comment> page) {
        super(page);
        this.content = page.getContent();
    }
}
