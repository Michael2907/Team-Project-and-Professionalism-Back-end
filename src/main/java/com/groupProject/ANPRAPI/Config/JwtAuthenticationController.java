package com.groupProject.ANPRAPI.Config;

import com.groupProject.ANPRAPI.Domain.AuthenticatedUser;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Authentication endpoint that is called when a user wants to log in
     *
     * POST request that takes in a username and password in the post request body
     *
     * @param authenticationRequest
     * @return A JWT token and the users credentials
     * @throws Exception
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        //Authenticate user with provided details
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        //Generate JWT Token
        final String token = jwtTokenUtil.generateToken(userDetails);

        User user = new User();
        user = userService.findUser(authenticationRequest.getUsername());
        //If user have been flagged as deleted then dont return a JWT
        if(user.getDeleted()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
            //Return JWT with User object
            AuthenticatedUser authenticatedUser = new AuthenticatedUser();
            authenticatedUser.setJwtToken(token);
            authenticatedUser.setUser(user);
            return ResponseEntity.ok(authenticatedUser);
        }
    }

    /**
     * Endpoint called to create a new user
     *
     * Takes in a User object
     * @param authenticationRequest
     * @return Returns a HTTP response code appropriate for action
     * @throws Exception
     */
    @RequestMapping(value = "/initialiseUser", method = RequestMethod.POST)
    public ResponseEntity initialiseUser(@RequestBody User authenticationRequest) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //If user is not a guest generate password and email the user
        if(authenticationRequest.getUserGroup() != 3){
            try{
                if(userService.findUserForNumberPlate(authenticationRequest.getNumberPlate())){
                    //If a user with the numberplate already exists throw a bad request
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                String subject = "New car park management account created";
                String password = RandomStringUtils.randomAlphabetic(10);
                userService.initialiseUser(authenticationRequest, passwordEncoder.encode(password));
                String text = "A new user account has been created for you in the car park management system\nPlease log in with the following details and change your " +
                        "password immediately\nUsername: " + authenticationRequest.getUsername() + "\nPassword: " + password;
                sendEmail(authenticationRequest.getEmail(), subject, text);
                return new ResponseEntity(HttpStatus.OK);
            }catch (Exception ex){
                //Catch if user can not be initalized
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }else{
            //Actioned if user will be a guest
            userService.initialiseUser(authenticationRequest, passwordEncoder.encode(authenticationRequest.getPassword()));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * Handles authentication of user
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    /**
     * Handles sending emails with java mail sender
     * @param email
     * @param subject
     * @param text
     */
    private void sendEmail(String email, String subject, String text){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}