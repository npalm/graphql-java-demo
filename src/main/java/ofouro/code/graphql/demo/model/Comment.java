package ofouro.code.graphql.demo.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity {

    @NonNull
    private String comment;

    private ZonedDateTime createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "talk_id")
    private Talk talk;

}
