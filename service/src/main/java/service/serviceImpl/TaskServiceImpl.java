package service.serviceImpl;

import dao.interfaceDao.TaskDao;
import entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.serviceInterdace.TaskService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    public List<Task> getAll() {
        return taskDao.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskDao.findById(id);
    }

    @Override
    public void update(Task task) {
        taskDao.update(task);
    }

    @Override
    public void delete(Task task) {
        taskDao.delete(task);
    }

    @Override
    public List<Task> findByProjectId(Long id) {
        return taskDao.findByProjectId(id);
    }
}
