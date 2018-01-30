package dto;

import entity.Authorization;
import entity.Project;
import entity.Role;
import entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDto {

    private Authorization authorization;
    private String firstName;
    private String lastName;
    private String mail;
    private Role role;
    private Set<Task> tasks = new HashSet<>();
    private Set<Project> projects = new HashSet<>();
}
