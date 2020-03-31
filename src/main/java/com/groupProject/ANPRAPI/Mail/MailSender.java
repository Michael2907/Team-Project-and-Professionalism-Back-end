package com.groupProject.ANPRAPI.Mail;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags="Mail Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/mail")
public class MailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("")
    private void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("jordanmarshall84@live.co.uk");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
}
