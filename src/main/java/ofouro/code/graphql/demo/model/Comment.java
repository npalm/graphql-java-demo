package ofouro.code.graphql.demo.model;


import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity {

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String comment;

    private ZonedDateTime createdOn;

    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "talk_id")
    private Talk talk;

}
