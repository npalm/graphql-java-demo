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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"talks"})
public class Person extends BaseEntity {

    private String name;

    private String githubAccount;

    private String blog;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "speakers")
    private List<Talk> talks;


}
