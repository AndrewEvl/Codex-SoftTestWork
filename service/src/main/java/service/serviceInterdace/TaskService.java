package service.serviceInterdace;

import entity.Task;

import java.util.List;

public interface TaskService {

    void save (Task task);

    List<Task> getAll ();

    Task findById (Long id);

    void update (Task task);

    void delete (Task task);


}
