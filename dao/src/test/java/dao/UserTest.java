package dao;

import dao.interfaceDao.ProjectDao;
import dao.interfaceDao.UserDao;
import entity.Authorization;
import entity.Project;
import entity.Role;
import entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectDao projectDao;

    @Test
    public void findByFirstAndLastName()throws Exception{
        User user = new User();
        user.setFirstName("Andrew");
        user.setLastName("Evlash");
        user.setRole(Role.DEVELOPER);
        user.setAuthorization(Authorization.YES);
        user.setMail("AAA");
        userDao.save(user);
        User byFirstAndLastName = userDao.fundByFirstAndLastName("Andrew", "Evlash");
        assertEquals(byFirstAndLastName.getFirstName(), "Andrew");
        assertEquals(byFirstAndLastName.getLastName(),"Evlash");
    }

    @Test
    public void saveUserTest() throws Exception{
        User user = new User();
        user.setFirstName("Andrew");
        user.setLastName("Evlash");
        user.setRole(Role.DEVELOPER);
        user.setAuthorization(Authorization.YES);
        user.setMail("AAA");
        userDao.save(user);
        Long id = user.getId();
        assertNotNull(userDao.findById(id));
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = new User();
        user.setFirstName("Andrew");
        user.setLastName("Evlash");
        user.setRole(Role.DEVELOPER);
        user.setAuthorization(Authorization.YES);
        user.setMail("AAA");
        userDao.save(user);
        Long id = user.getId();
        assertNotNull(userDao.findById(id));
        userDao.delete(user);
        assertNull(userDao.findById(id));
    }

    @Test
    public void updateAndFindByIdUserTest() throws Exception{
        User user = new User();
        user.setLastName("Evlash");
        userDao.save(user);
        Long id = user.getId();
        user.setLastName("update");
        userDao.update(user);
        assertEquals(userDao.findById(id).getLastName(),"update");
    }
    @Test
    public void projectSaveTest (){
        Project project = new Project();
        project.setName("Task Tracker");
        projectDao.save(project);
        Long id = project.getId();
        assertNotNull(projectDao.findById(id));
    }

    @Test
    public void userFindByTokenTest (){
        User user = new User();
        user.setToken("123456789");
        userDao.save(user);
        User byToken = userDao.findByToken("123456789");
        assertEquals(byToken.getToken(),"123456789");
    }
}
