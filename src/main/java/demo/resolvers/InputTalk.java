package demo.resolvers;

import demo.model.Talk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputTalk {

    private String title;
    private String summary;

    Talk convert() {
        return new Talk(this.title, this.summary, null, null, null);
    }
}
