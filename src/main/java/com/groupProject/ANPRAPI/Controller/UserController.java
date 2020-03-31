package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Config.JwtRequest;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags="User Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("")
    private List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/guests")
    private List<User> getGuestUsers(){
        List<User> allUsers = userService.findAll();
        List<User> guestUsers = new ArrayList();
        for(User user : allUsers){
            if(user.getUserGroup() == 3){
                guestUsers.add(user);
            }
        }
        return guestUsers;
    }

    @PutMapping("")
    private String updateUsers(@RequestBody User user) throws Exception {
        try{
            userService.update(user);
            return "success";
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/mail")
    private void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("jordanmarshall84@live.co.uk", "w16012082@northumbria.ac.uk");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
    }

    @PutMapping("/changePassword")
    private ResponseEntity changePassword(@RequestBody JwtRequest user, @RequestParam String newPassword) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        User foundUser = userService.findUser(user.getUsername());
        foundUser.setPassword(passwordEncoder.encode(newPassword));
        foundUser.setInitialised(true);
        userService.update(foundUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
