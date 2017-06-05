package demo.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class Person {

    @Id
    @GeneratedValue
    private final Long id = null;

    @NotEmpty
    @NonNull
    private String name;

    private String githubAccount;

    private String blog;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "speakers")
    private Collection<Talk> talks;
}
