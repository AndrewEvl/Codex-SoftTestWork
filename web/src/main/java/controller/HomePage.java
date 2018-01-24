package controller;


import entity.Authorization;
import entity.User;
import org.apache.commons.validator.routines.EmailValidator;
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

    @Autowired
    public HomePage(UserService userService) {
        this.userService = userService;
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
    public String testPage(User user, Model model) throws Exception {
        boolean emailValidator = EmailValidator.getInstance().isValid(user.getMail());
        if (!emailValidator){
            return "redirect:/user-error";
        }
        user.setAuthorization(Authorization.NO);
        userService.save(user);
        model.addAttribute("user",user);
        return "testPage";
    }

    @GetMapping("/user-error")
    public String userErrorGet(){
        return "ErrorValidateMail";
    }
}
