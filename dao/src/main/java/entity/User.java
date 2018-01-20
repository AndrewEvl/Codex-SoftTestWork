package entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
//@ToString
@NoArgsConstructor
//@Data
public class User extends BaseEntity {

    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private Role role;
    @Getter
    @Setter
    private List<Task> tasks;
    @Getter
    @Setter
    private List<Project> projects;

    public User(String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
