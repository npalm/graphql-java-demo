package demo.resolvers;

import demo.model.Conference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
