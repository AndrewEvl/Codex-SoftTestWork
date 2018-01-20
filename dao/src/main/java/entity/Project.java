package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@NoArgsConstructor
@Data
public class Project extends BaseEntity {

    private String name;
    private Set<User> users = new HashSet<>();
    private User user;
    private Set<Task> tasks;

}
