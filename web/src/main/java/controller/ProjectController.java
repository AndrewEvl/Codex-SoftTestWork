package controller;

import dto.ProjectDto;
import entity.Project;
import entity.Status;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.ProjectService;
import service.serviceInterdace.UserService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
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
        List<Status> statusList = Arrays.asList(Status.IMPLEMENTATION, Status.RELEASING, Status.VERIFYING, Status.WAITING);
        model.addAttribute("allStatus", statusList);
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
        project.setStatus(projectDto.getStatusId());
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
        model.addAttribute("ProjectId", id);
        return "redirect:/project-info/{id}";
    }

    @GetMapping("/project-info/{id}")
    public String projectInfoIdGet (@PathVariable("id") Long id, Model model){
        Project project = projectService.findById(id);
        String name = project.getName();
        Status status = project.getStatus();
        User userCreator = project.getUserCreator();
        List<User> users = project.getUsers();
        model.addAttribute("nameProject", name);
        model.addAttribute("statusProject",status);
        model.addAttribute("userCreator", userCreator);
        model.addAttribute("allUsers",users);
        return "projectInfoPage";
    }
}
