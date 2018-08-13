package demo.resolvers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputComment {

    private Long talkId;
    private Long authorId;
    private String comment;
}
