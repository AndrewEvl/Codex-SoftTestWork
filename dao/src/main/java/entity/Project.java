package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(exclude = "userCreator")
@NoArgsConstructor
@Data
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @Column(name = "satus")
    private Status status;
    @OneToOne
    @JoinColumn(name = "user_creator_id")
    private User userCreator;

}
