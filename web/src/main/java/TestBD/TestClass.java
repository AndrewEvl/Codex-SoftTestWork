package TestBD;

import dao.interfaceDao.UserDao;
import entity.Comment;
import entity.Project;
import entity.Task;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.serviceInterdace.UserService;

public class TestClass {

    public static void main(String[] args) {
        Project project = new Project();
        User user = new User();
        Task task = new Task();
        Comment comment = new Comment();
    }
}
