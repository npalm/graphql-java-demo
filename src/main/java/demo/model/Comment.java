package demo.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class Comment {

    @Id
    @GeneratedValue
    private final Long id = null;

    @NonNull
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "talk_id")
    private Talk talk;
}
