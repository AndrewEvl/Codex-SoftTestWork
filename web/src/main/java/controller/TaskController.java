package controller;

import dto.TaskDto;
import entity.Project;
import entity.Status;
import entity.Task;
import entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.ProjectService;
import service.serviceInterdace.TaskService;
import service.serviceInterdace.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @ModelAttribute("task")
    public Task task(){
        return new Task();
    }

    @ModelAttribute("taskDto")
    public TaskDto taskDto(){
        return new TaskDto();
    }

    @GetMapping("/task-create")
    public String taskSaveGet (Model model){
        List<Project> projectList = projectService.getAll();
        List<User> userList = userService.getAll();
        List<Status> statusList = Arrays.asList(Status.IMPLEMENTATION, Status.RELEASING, Status.VERIFYING, Status.WAITING);
        model.addAttribute("allProject", projectList);
        model.addAttribute("allUsers",userList);
        model.addAttribute("statusList",statusList);
        return "taskSavePage";
    }

    @PostMapping("/task-create")
    public String taskSavePost (TaskDto taskDto, Model model){
        Long projectId = taskDto.getProjectId();
        String taskName = taskDto.getTaskName();
        Long usersId = taskDto.getUsersId();
        Set<User> userList = new HashSet<>();
        User byId = userService.findById(usersId);
        userList.add(byId);
        Task task = new Task();
        task.setProject(projectService.findById(projectId));
        task.setStatus(taskDto.getStatusId());
        task.setUser(userList);
        task.setText(taskName);
        taskService.save(task);
        return "redirect:/";
    }

    @GetMapping("/task-list")
    public String taskListGet (Model model){
        List<Task> taskList = taskService.getAll();
        model.addAttribute("allTask",taskList);
        return "taskListPage";
    }

    @PostMapping("/task-list")
    public String taskInfoPost (Task task, Model model){
        Long id = task.getId();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userMail = user.getMail();
        model.addAttribute("id", id);
        return "redirect:/task-info/{id}";
    }



}
