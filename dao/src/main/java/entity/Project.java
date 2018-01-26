package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(exclude = "userCreator")
@NoArgsConstructor
@Data
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<User> users;

    @Column(name = "satus")
    private Status status;
    @OneToOne
    @JoinColumn(name = "user_creator_id")
    private User userCreator;

}
