package service.serviceInterdace;

import dto.TaskDto;
import entity.Task;
import entity.User;

import java.util.List;

public interface TaskService {

    void save (Task task);

    List<Task> getAll ();

    Task findById (Long id);

    void update (Task task);

    void delete (Task task);

    List<Task> findByProjectId(Long id);

    TaskDto findByIdDto (Long id);

}
