package controller;

import entity.Authorization;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.ProjectService;
import service.serviceInterdace.TaskService;
import service.serviceInterdace.UserService;

@Controller
public class HomePage {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public HomePage(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @GetMapping("/user-save")
    public String userSaveGet(){
        return "testPage";
    }

    @PostMapping("/user-save")
    public String testPage(User user, Model model) {
        user.setAuthorization(Authorization.NO);
        userService.save(user);
        model.addAttribute("user",user);
        return "testPage";
    }
}
