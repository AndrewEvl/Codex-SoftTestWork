package service.serviceImpl;

import dao.interfaceDao.ProjectDao;
import entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.serviceInterdace.ProjectService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    @Override
    public List<Project> getAll() {
        return projectDao.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public void update(Project project) {
        projectDao.update(project);
    }

    @Override
    public void delete(Project project) {
        projectDao.delete(project);
    }
}
