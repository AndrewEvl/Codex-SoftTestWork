package dao;

import dao.common.BaseDao;
import dao.daoImpl.UserDaoImpl;
import dao.interfaceDao.CommentDao;
import dao.interfaceDao.ProjectDao;
import dao.interfaceDao.TaskDao;
import dao.interfaceDao.UserDao;
import entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private CommentDao commentDao;

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
    public void allSystemTest() throws Exception{
        User user = new User();
        User userSecond = new User();
        Project project = new Project();
        Task task = new Task();
        Comment comment = new Comment();

        userSecond.setFirstName("Jon");
        userSecond.setLastName("Doy");
        userSecond.setRole(Role.DEVELOPER);
        userSecond.setAuthorization(Authorization.YES);
        userSecond.setMail("mail@mail.com");
        userDao.save(userSecond);

        user.setFirstName("Andrew");
        user.setLastName("Evlash");
        user.setRole(Role.MANAGER);
        user.setAuthorization(Authorization.YES);
        user.setMail("mail@mail.com");
        userDao.save(user);

        List<User> userList = userDao.findAll();

        project.setName("Task Tracker");
        project.setUsers(userList);
        project.setUserCreator(user);
        projectDao.save(project);

        task.setTest("work");
        task.setStatus(Status.RELEASING);
        Project byId = projectDao.findById(1L);
        task.setProject(byId);
        taskDao.save(task);
//        List<Task> taskList = taskDao.findAll();
//        User userDaoById = userDao.findById(1L);
//        user.setTasks(taskList);
//        userDao.save(user);
//
//        comment.setTask(task);
//        comment.setText("Comments");
//        comment.setUser(user);
//        commentDao.save(comment);

//        System.out.println(project);
//        System.out.println(user);
//        System.out.println(task);
//        System.out.println(comment);

    }
}
