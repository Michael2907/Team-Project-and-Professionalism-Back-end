package com.groupProject.ANPRAPI.Config;

import com.groupProject.ANPRAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that is used to handle retrieval of user details
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<com.groupProject.ANPRAPI.Domain.User> users = this.userRepository.findAll();

        for(com.groupProject.ANPRAPI.Domain.User user : users){
            if(user.getUsername().equals(username)){
                return new User(user.getUsername(), user.getPassword(),
                        new ArrayList<>());
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}