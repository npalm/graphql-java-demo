package demo.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class Talk {

    @Id
    @GeneratedValue
    private final Long id = null;

    @NotEmpty
    @NonNull
    private String title;

    private String summary;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Person> speakers = new HashSet<>();

}
