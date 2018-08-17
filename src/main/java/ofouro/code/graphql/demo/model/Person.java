package ofouro.code.graphql.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "talks"})
public class Person {

    @Id
    @GeneratedValue
    private final Long id = null;

    private String name;

    private String githubAccount;

    private String blog;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "speakers")
    private List<Talk> talks;


}
