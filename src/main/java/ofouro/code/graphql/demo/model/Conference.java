package ofouro.code.graphql.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Conference extends BaseEntity {

    @NonNull
    private String name;

    private String city;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "conferences")
    private List<Talk> talks;
}
