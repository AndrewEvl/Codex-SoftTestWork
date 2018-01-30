package dao.daoImpl;

import com.querydsl.jpa.impl.JPAQuery;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.TaskDao;
import entity.QTask;
import entity.Task;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl extends BaseDaoImpl<Task> implements TaskDao {


    @Override
    public List<Task> findByProjectId(Long id) {
        Session session = getSessionFactory().getCurrentSession();
        QTask qTask = new QTask("myTask");
        JPAQuery<Task> query = new JPAQuery<>(session);
        query.select(qTask).from(qTask).where(qTask.project.id.eq(id));
        return query.fetchAll().fetch();
    }
}
