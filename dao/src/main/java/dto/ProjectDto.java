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
public class ProjectDto {

private String nameProject;
private Long usersId;
private Long userCreatorId;
private Status StatusId;
}
