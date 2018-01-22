package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@NoArgsConstructor
@Data
@Table(name = "user")
public class User extends BaseEntity {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private Role role;

    private String mail;

    private Authorization authorization;

    @ManyToMany
    @JoinTable(name = "task_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Project> projects = new HashSet<>();
}
