package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "users")
@Entity
@ToString(exclude = {"userCreator","users"})
@NoArgsConstructor
@Data
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_creator_id")
    private User userCreator;

}
