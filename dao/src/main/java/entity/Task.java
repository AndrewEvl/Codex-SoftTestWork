package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "user")
@Entity
@ToString(exclude = "user")
@NoArgsConstructor
@Data
@Table (name = "task")
public class Task extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @ManyToMany
    @JoinTable(name = "user_task",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "status")
    private Status status;
}
