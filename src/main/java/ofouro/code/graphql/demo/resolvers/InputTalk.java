package ofouro.code.graphql.demo.resolvers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ofouro.code.graphql.demo.model.Talk;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputTalk {

    private String title;
    private String summary;

    Talk convert() {
        return convert(this);
    }

    static Talk convert(InputTalk talk) {
        return talk != null ? new Talk(talk.getTitle(), talk.getSummary(), null, null) : null;
    }

}
