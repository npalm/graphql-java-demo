package demo.resolvers;

import demo.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPerson {

    private String name;
    private String blog;
    private String githubAccount;

    Person convert() {
        return new Person(this.name, this.githubAccount, this.blog, null);
    }
}
