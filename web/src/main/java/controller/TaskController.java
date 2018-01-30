package controller;

import dto.TaskDto;
import entity.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.CommentService;
import service.serviceInterdace.ProjectService;
import service.serviceInterdace.TaskService;
import service.serviceInterdace.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class TaskController {

    private Long ID;

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;
    private final CommentService commentService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService, CommentService commentService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @ModelAttribute("task")
    public Task task(){
        return new Task();
    }

    @ModelAttribute("comment")
    public Comment comment(){
        return new Comment();
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
        User user = new User();
        Long projectId = taskDto.getProjectId();
        Long usersId = taskDto.getUsersId();
        User byId = userService.findById(usersId);
//        Set<User> userList = new HashSet<>();
//        UserDto byIdDto = userService.findByIdDto(usersId);
//        user.setLastName(byIdDto.getLastName());
//        user.setFirstName(byIdDto.getFirstName());
//        user.setRole(byIdDto.getRole());
//        user.setProjects(byIdDto.getProjects());
//        user.setTasks(byIdDto.getTasks());
//        userList.add(user);
        Task task = new Task();
        task.setProject(projectService.findById(projectId));
        task.setStatus(taskDto.getStatusId());
        task.getUser().add(byId);
        task.setText(taskDto.getTaskText());
        task.setName(taskDto.getTaskName());
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
        model.addAttribute("id", id);
        return "redirect:/task-info/{id}";
    }

    @GetMapping("/task-info")
    public String taskInfoGet (Task task, Model model){
        Long id = task.getId();
        model.addAttribute("id",id);
        return "redirect:/task-info/{id}";
    }

    @GetMapping("/task-info/{id}")
    public String taskInfoIdGet (@PathVariable ("id") Long id, Model model){
        List<Comment> commentsByTaskId = commentService.findByTaskId(id);
        ID = id;
        Task taskById = taskService.findById(id);
        model.addAttribute("allComments", commentsByTaskId);
        model.addAttribute("task",taskById);
        return "taskInfoPage";
    }

    @GetMapping("/task-info/add-comment")
    public String addCommentGet (){
        return "addCommentTaskPage";
    }

    @PostMapping("/task-info/add-comment")
    public String addCommentPost (Comment comment,Model model) {
        Task taskById = taskService.findById(ID);
        User userByMail = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        comment.setTask(taskById);
        comment.setUser(userByMail);
        commentService.save(comment);
        model.addAttribute("id",ID);
        return "redirect:/task-info/{id}";
    }



}
