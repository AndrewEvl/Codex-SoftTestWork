package dto;

import entity.Project;
import entity.Status;
import entity.User;
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
public class TaskDto {

    private String taskName;
    private String taskText;
    private Status statusId;
    private Long projectId;
    private Long usersId;
    private Set<User> users = new HashSet<>();
    private Set<Project> projects = new HashSet<>();
}
