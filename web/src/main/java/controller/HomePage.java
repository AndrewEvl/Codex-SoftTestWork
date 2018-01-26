package controller;


import entity.Authorization;
import entity.Role;
import entity.User;
import mail.MailSender;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInterdace.UserService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Security;
import java.util.Arrays;
import java.util.List;
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
        model.addAttribute("user", mailConfirmationUser);
        return "testPage";
    }

    @GetMapping("/user-error")
    public String userErrorGet() {
        return "ErrorValidateMail";
    }

    @GetMapping("/user-successful/{token}")
    public String userSuccessfulGet() {
        return "successfulUserPage";
    }

    @PostMapping("/user-successful/{token}")
    public String userSuccessfulPost(@PathVariable("token") String token) {
        User userByToken = userService.findByToken(token);
        userByToken.setAuthorization(Authorization.YES);
        userService.update(userByToken);
        return "successfulUserPage";
    }


    public synchronized User mailConfirmation(User user) throws MessagingException {

//        public synchronized void sendMail (String subject, String body, String sender, String recipients) throws
//        Exception {
        String host = "smtp.gmail.com";

            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("3592401@gmail.com", "andrewevlash");
                        }
                    });

            MimeMessage message = new MimeMessage(session);
            message.setSender(new InternetAddress("3592401@gmail.com"));
            message.setSubject("Hello");
            message.setContent("Text", "text/plain");
            if (user.getMail().indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getMail()));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));


            Transport.send(message);

        return user;
        }


//        try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {
//            context.load("classpath:applicationContext.xml");
//            context.refresh();
//            JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
//            SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);
//
//            // Создаём потокобезопасную копию шаблона.
//            SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
//
//
//            mailMessage.setTo(user.getMail());
//            String generationLink = mailGenerationLink();
//            user.setToken(generationLink);
//
//            mailMessage.setText("http://localhost:8080/user-successful/" + generationLink);
//            try {
//                mailSender.send(mailMessage);
//                System.out.println("Mail sended");
//            } catch (MailException mailException) {
//                System.out.println("Mail send failed.");
//                mailException.printStackTrace();
//            }
//        }


//        final Properties properties = new Properties();
//        properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

//        String userMail = user.getMail();
//        Session mailSession = Session.getDefaultInstance(properties);
//
//        String generationLink = mailGenerationLink();
//        user.setToken(generationLink);
//        MimeMessage message = new MimeMessage(mailSession);
////        andrewevl298@gmail.com
//        message.setFrom(new InternetAddress("3592401@gmail.com"));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
//        message.setSubject("Hello");
//        message.setText("http://localhost:8080/user-successful/" + generationLink);
//
//        Transport transport = mailSession.getTransport();
//        transport.connect(null, "andrewevlash");
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
//        return user;

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
