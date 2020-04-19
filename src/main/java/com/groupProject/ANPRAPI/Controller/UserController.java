package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Config.JwtRequest;
import com.groupProject.ANPRAPI.Domain.EventLog;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Service.EventLogService;
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
import java.util.Calendar;
import java.util.List;

/**
 * REST controller to handle all user interactions
 */
@Api(tags="User Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private EventLogService eventLogService;

    /**
     * GET request to return all user information
     * @return List of users
     */
    @GetMapping("")
    private List<User> getUsers(){
        return userService.findAll();
    }

    /**
     * GET request to return all users with guest user group
     * @return list of user objects
     */
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

    /**
     * PUT request to update a users information
     * @param user
     * @param userID
     * @return String of success or fail
     * @throws Exception
     */
    @PutMapping("")
    private String updateUsers(@RequestBody User user, @RequestParam Integer userID) throws Exception {
        try{
            userService.update(user);
            //Log event
            EventLog eventLog = new EventLog();
            eventLog.setUserID(userID);
            eventLog.setDescription("User: " + user.getUsername() + " update/created by user: " + userID);
            Calendar currenttime = Calendar.getInstance();
            java.sql.Timestamp sqldate = new java.sql.Timestamp((currenttime.getTime()).getTime());
            eventLog.setDateTime(sqldate);
            eventLogService.save(eventLog);
            return "success";
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    /**
     * PUT request to change a users password
     * @param user
     * @param newPassword
     * @return Success of fail HTTP status
     * @throws Exception
     */
    @PutMapping("/changePassword")
    private ResponseEntity changePassword(@RequestBody JwtRequest user, @RequestParam String newPassword) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try {
            //Try log the user in
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        //If user has logged in update password
        User foundUser = userService.findUser(user.getUsername());
        foundUser.setPassword(passwordEncoder.encode(newPassword));
        foundUser.setInitialised(true);
        userService.update(foundUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
