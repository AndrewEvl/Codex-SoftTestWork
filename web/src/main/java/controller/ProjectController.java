package controller;

import dto.ProjectDto;
import entity.Project;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.ProjectService;

import java.util.List;
import java.util.Set;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ModelAttribute("project")
    public Project project(){
        return new Project();
    }

    @GetMapping("/project-save")
    public String projectSaveGet (){
        return "projectSave";
    }

    @PostMapping("/project-save")
    public String projectSavePost (Project project,User user, Model model){
        ProjectDto projectDto = new ProjectDto();
        List<Project> projectList =projectService.getAll();

        projectService.save(project);
        model.addAttribute("projects", project);
        model.addAttribute("allProject", projectList);
        return "projectSave";
    }
}
