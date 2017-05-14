package demo.model;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLDescription;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLNonNull;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@GraphQLObject("Talk")
@EqualsAndHashCode(exclude = {"id"})
public class Talk {

    @Id
    @GeneratedValue
    @GraphQLNonNull
    private final Long id = null;

    @NotEmpty
    @NonNull
    @GraphQLNonNull
    @GraphQLDescription("Title of the talk")
    private String title;

    @GraphQLDescription("Summary of the talk")
    private String summary;

    @ManyToMany(fetch = FetchType.EAGER)
    @GraphQLDescription("Speakers of the talk")
    private Collection<Person> speakers = new HashSet<>();

}
