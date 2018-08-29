package ofouro.code.graphql.demo.resolvers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ofouro.code.graphql.demo.model.Conference;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputConference {

    private String name;
    private String city;

    public static Conference convert(InputConference conference) {
        return conference != null ? new Conference(conference.getName(), conference.getCity(), null) : null;

    }

    Conference convert() {
        return convert(this);
    }
}
