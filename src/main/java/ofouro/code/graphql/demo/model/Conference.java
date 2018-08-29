package ofouro.code.graphql.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Conference extends BaseEntity {

    private String name;

    private String city;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "conferences")
    private List<Talk> talks;
}
