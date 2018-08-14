package demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "talks"})
public class Person {

    @Id
    @GeneratedValue
    private final Long id = null;

    @NonNull
    private String name;

    private String githubAccount;

    private String blog;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "speakers")
    private List<Talk> talks;


}
