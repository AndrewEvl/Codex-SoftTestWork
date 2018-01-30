package dao.interfaceDao;

import dao.common.BaseDao;
import entity.Task;

import java.util.List;

public interface TaskDao extends BaseDao<Task> {

    List<Task> findByProjectId (Long id);
}
