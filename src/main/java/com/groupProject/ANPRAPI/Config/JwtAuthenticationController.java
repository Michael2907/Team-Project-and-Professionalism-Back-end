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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        User user = new User();
        user = userService.findUser(authenticationRequest.getUsername());
        if(user.getDeleted()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
            AuthenticatedUser authenticatedUser = new AuthenticatedUser();
            authenticatedUser.setJwtToken(token);
            authenticatedUser.setUser(user);
            return ResponseEntity.ok(authenticatedUser);
        }
    }

    @RequestMapping(value = "/initialiseUser", method = RequestMethod.POST)
    public ResponseEntity initialiseUser(@RequestBody User authenticationRequest) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(authenticationRequest.getUserGroup() != 3){
            try{
                String subject = "New car park management account created";
                String password = RandomStringUtils.randomAlphabetic(10);
                userService.initialiseUser(authenticationRequest, passwordEncoder.encode(password));
                String text = "A new user account has been created for you in the car park management system\nPlease log in with the following details and change your " +
                        "password immediately\nUsername: " + authenticationRequest.getUsername() + "\nPassword: " + password;
                sendEmail(authenticationRequest.getEmail(), subject, text);
                return new ResponseEntity(HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }else{
            userService.initialiseUser(authenticationRequest, passwordEncoder.encode(authenticationRequest.getPassword()));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private void sendEmail(String email, String subject, String text){
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}