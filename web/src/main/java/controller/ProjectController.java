package controller;

import dto.ProjectDto;
import entity.Project;
import entity.Task;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.ProjectService;
import service.serviceInterdace.TaskService;
import service.serviceInterdace.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @ModelAttribute("project")
    public Project project(){
        return new Project();
    }

    @ModelAttribute("projectDto")
    public ProjectDto projectDto(){
        return new ProjectDto();
    }

    @GetMapping("/project-save")
    public String projectSaveGet (Model model){
        List<User> userList = userService.getAll();
        model.addAttribute("allUsers", userList);
        return "projectSave";
    }

    @PostMapping("/project-save")
    public String projectSavePost (ProjectDto projectDto, Model model){
        Project project = new Project();
        Long usersId = projectDto.getUsersId();
        User userById= userService.findById(usersId);
        project.setName(projectDto.getNameProject());
        project.setUserCreator(userById);
        projectService.save(project);
        model.addAttribute("projects", project);
        return "redirect:project-save-successful";
    }

    @GetMapping("/project-save-successful")
    public String ProjectSaveSuccessful (){
        return "projectSaveSuccessful";
    }

    @GetMapping("/project-info")
    public String projectInfoGet (Project project, Model model){
        Long id = project.getId();
        model.addAttribute("id", id);
        return "redirect:/project-info/{id}";
    }

    @GetMapping("/project-info/{id}")
    public String projectInfoIdGet (@PathVariable("id") Long id, Model model){
        Project project = projectService.findById(id);
        List<Task> taskList = taskService.findByProjectId(id);
        User userCreator = project.getUserCreator();
        Set<User> users = project.getUsers();
        model.addAttribute("user",userCreator);
        model.addAttribute("project", project);
        model.addAttribute("allTask", taskList);
        model.addAttribute("allUsers",users);
        return "projectInfoPage";
    }


    @GetMapping("/project-list")
    public String projectListGet (Model model){
        List<Project> projectList = projectService.getAll();
        model.addAttribute("allProject", projectList);
        return "projectListPage";
    }

    @PostMapping("/project-list")
    public String projectListPost (Model model, Project project){
        Long id = project.getId();
        model.addAttribute("id",id);
        return "redirect:/project-info/{id}";
    }
}
