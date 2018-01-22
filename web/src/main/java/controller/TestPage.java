package controller;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import service.serviceInterdace.ProjectService;
import service.serviceInterdace.TaskService;
import service.serviceInterdace.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class TestPage {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public TestPage(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String testPage() {
        User user = new User();
        Project project = new Project();
        Task task = new Task();
        project.setName("Task Tracker1");
        user.setFirstName("Andrew1");
        user.setLastName("Evlas1");
        user.setRole(Role.MANAGER);
        userService.save(user);

        project.setUserCreator(user);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);



        projectService.save(project);
        project.setUsers(userSet);
        projectService.save(project);

        task.setProject(project);
        task.setStatus(Status.IMPLEMENTATION);
        task.setTest("Work this project1");
        Set<User> userSetTask = new HashSet<>();
        userSetTask.add(user);
        task.setUserSet(userSetTask);
        taskService.save(task);

        return "testPage";
    }
}
