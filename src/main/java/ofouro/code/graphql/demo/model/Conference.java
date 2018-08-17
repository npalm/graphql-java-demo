package ofouro.code.graphql.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class Conference {

    @Id
    @GeneratedValue
    private final Long id = null;

    @NonNull
    private String name;

    private String city;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "conferences")
    private List<Talk> talks;
}
