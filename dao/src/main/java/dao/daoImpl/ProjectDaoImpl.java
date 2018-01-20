package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.ProjectDao;
import entity.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {
}
