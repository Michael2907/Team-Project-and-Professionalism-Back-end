package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Repository.UserRepository;
import com.groupProject.ANPRAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialiseUser(String username, String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        this.userRepository.save(user);
    }
}
