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
@ToString(exclude = {"projects","tasks","projects"})
@NoArgsConstructor
@Data
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;

    @Column(name = "mail")
    private String mail;

    @Column(name = "token")
    private String token;

    @Column(name = "authorization")
    private Authorization authorization;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_task",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Project> projects;
}
