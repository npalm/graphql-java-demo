package ofouro.code.graphql.demo.resolvers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputComment {

    private Long talkId;
    private String author;
    private String comment;
}
