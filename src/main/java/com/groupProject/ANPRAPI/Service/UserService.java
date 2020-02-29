package com.groupProject.ANPRAPI.Service;

import com.groupProject.ANPRAPI.Domain.User;

import java.util.List;

public interface UserService {

    void initialiseUser(String username, String password);

    List<User> findAll();
}
