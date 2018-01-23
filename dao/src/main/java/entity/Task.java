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
@ToString
@NoArgsConstructor
@Data
@Table (name = "task")
public class Task extends BaseEntity {

    @Column(name = "text")
    private String test;

    @ManyToMany(mappedBy = "tasks")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "status")
    private Status status;
}
