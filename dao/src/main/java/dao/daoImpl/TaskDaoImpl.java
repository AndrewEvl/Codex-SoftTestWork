package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.TaskDao;
import entity.Task;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl extends BaseDaoImpl<Task> implements TaskDao {
}
