package controller;


import dto.UserDto;
import entity.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.UserService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {

    private final UserService userService;

    private final SimpleMailMessage simpleMailMessage;

    @Autowired
    public UserController(UserService userService, SimpleMailMessage simpleMailMessage) {
        this.userService = userService;
        this.simpleMailMessage = simpleMailMessage;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/user-save")
    public String userSaveGet(Model model) {
        List<Role> roles = Arrays.asList(Role.DEVELOPER, Role.MANAGER);
        model.addAttribute("allRoles", roles);
        return "testPage";
    }

    @PostMapping("/user-save")
    public String testPage(User user, Model model) throws Exception {
        boolean emailValidator = EmailValidator.getInstance().isValid(user.getMail());
        if (!emailValidator) {
            return "redirect:/user-error";
        }
        user.setAuthorization(Authorization.NO);
        User mailConfirmationUser = mailConfirmation(user);
        userService.save(mailConfirmationUser);
        String token = user.getToken();
        model.addAttribute("token",token);
        model.addAttribute("user", mailConfirmationUser);
        return "redirect:/user-successful/{token}";
    }

    @GetMapping("/user-error")
    public String userErrorGet() {
        return "errorValidateMail";
    }

    @GetMapping("/tokenLink/{token}")
    public String tokenLinkGet (@PathVariable("token") String token,Model model){
        String activLink = "http://localhost:8080/user-successful/"+ token;
        model.addAttribute("token",activLink);
        return "tokenLinkPage";
    }

    @GetMapping("/user-successful/{token}")
    public String userSuccessfulGet(@PathVariable("token") String token) {
        User userByToken = userService.findByToken(token);
        userByToken.setAuthorization(Authorization.YES);
        userService.update(userByToken);
        return "successfulUserPage";
    }

    @GetMapping("/login")
    public String userLoginGet (){
        return "user-login";
    }

    @GetMapping("/")
    public String homePageGet (){
        return "homePage";
    }

    @GetMapping("/user-info")
    public String userInfoGet(Model model){
        User userByMail = userService.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        Long id = userByMail.getId();
        model.addAttribute("id",id);
        return "redirect:/user-info/{id}";
    }

    @GetMapping("/user-info/{id}")
    public String userInfoIdGet (@PathVariable ("id") Long id, Model model){
        User byId = userService.findById(id);
        UserDto byIdDto = userService.findByIdDto(id);
        Set<Task> tasks = byId.getTasks();
        Set<Project> projects = byId.getProjects();
        model.addAttribute("allTask", tasks);
        model.addAttribute("allProject", projects);
        model.addAttribute("user",byId);
        return "userInfoPage";
    }


    private User mailConfirmation(User user) throws MessagingException, IOException {
        final Properties properties = new Properties();
        properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

        String userMail = user.getMail();
        Session mailSession = Session.getDefaultInstance(properties);

        String generationLink = mailGenerationLink();
        user.setToken(generationLink);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("3592401@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        message.setSubject("Hello");
        message.setText("http://localhost:8080/user-successful/" + generationLink);

        Transport transport = mailSession.getTransport();
        transport.connect(null, "andrewevlash");
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();

        //Second method realisation
        user.setToken(generationLink);
        simpleMailMessage.setTo(user.getMail());
        simpleMailMessage.setText("http://localhost:8080/user-successful" + generationLink);
        return user;
    }


    private String mailGenerationLink() {
        int length = 12;
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
