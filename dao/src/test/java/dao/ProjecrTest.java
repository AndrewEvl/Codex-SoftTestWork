package dao;

import dao.interfaceDao.ProjectDao;
import dao.interfaceDao.UserDao;
import entity.Project;
import entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ProjecrTest extends BaseTest {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void saveProjectTest () {
        Project project = new Project();
        User user = new User();
        user.setLastName("TEst");
        user.setFirstName("test");
        userDao.save(user);
        project.setName("Test");
        projectDao.save(project);
        Long id = project.getId();
        assertEquals(projectDao.findById(id), project);
    }
}
