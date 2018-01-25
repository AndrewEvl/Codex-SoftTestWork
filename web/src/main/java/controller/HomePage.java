package controller;


import entity.Authorization;
import entity.User;
import mail.MailSender;
import mail.Test;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Properties;
import java.util.Random;

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
        User mailConfirmationUser = mailConfirmation(user);
        userService.save(mailConfirmationUser);
        model.addAttribute("user",mailConfirmationUser);
        return "testPage";
    }

    @GetMapping("/user-error")
    public String userErrorGet(){
        return "ErrorValidateMail";
    }
    @GetMapping("/user-successful/{token}")
    public String userSuccessfulGet(){
        return "successfulUserPage";
    }

    @PostMapping("/user-successful/{token}")
    public String userSuccessfulPost(@PathVariable("token") String token){
        User userByToken = userService.findByToken(token);
        userByToken.setAuthorization(Authorization.YES);
        userService.update(userByToken);
        return "successfulUserPage";
    }



    private User mailConfirmation(User user) throws IOException, MessagingException {
        final Properties properties = new Properties();
        properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

        String userMail = user.getMail();
        Session mailSession = Session.getDefaultInstance(properties);

        String generationLink = mailGenerationLink();
        user.setToken(generationLink);
        MimeMessage message = new MimeMessage(mailSession);
        Test test = new Test();
//        andrewevl298@gmail.com
        message.setFrom(new InternetAddress("3592401@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        message.setSubject("Hello");
        message.setText("http://localhost:8080/user-successful/" + generationLink);

        Transport transport = mailSession.getTransport();
        transport.connect(null, "andrewevlash");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
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
