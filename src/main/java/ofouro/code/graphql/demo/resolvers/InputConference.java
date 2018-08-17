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

    Conference convert() {
        return new Conference(this.name, this.city, null);
    }
}
