package demo.model;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLDescription;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLNonNull;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@GraphQLObject("Person")
@EqualsAndHashCode(exclude = {"id"})
public class Person {

    @Id
    @GeneratedValue
    @GraphQLNonNull
    @GraphQLDescription("Person id")
    private final Long id = null;

    @NotEmpty
    @NonNull
    @GraphQLNonNull
    @GraphQLDescription("Name of the person")
    private String name;

    @GraphQLField("githubAccount")
    @GraphQLDescription("GitHub account")
    private String githubAccount;

    @GraphQLField("blog")
    @GraphQLDescription("URL to an awesome blog.")
    private String blog;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "speakers")
    @GraphQLDescription("Talks of the person")
    private Collection<Talk> talks;
}
