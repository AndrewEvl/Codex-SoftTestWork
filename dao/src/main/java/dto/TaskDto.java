package dto;

import entity.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
