package demo.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Person> speakers = new ArrayList<>();

}
