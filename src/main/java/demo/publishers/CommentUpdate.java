package demo.publishers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentUpdate {

    private String comment;
    private String authorName;
    private String talkTitle;
    private String timeStamp;
}
