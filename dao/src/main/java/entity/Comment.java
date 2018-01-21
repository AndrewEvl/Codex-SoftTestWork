package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@NoArgsConstructor
@Data
@Table (name = "comments")
public class Comment extends BaseEntity {

    @Column(name = "text")
    private String text;
    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
