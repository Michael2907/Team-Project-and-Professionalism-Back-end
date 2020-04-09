package com.groupProject.ANPRAPI.Service;

import com.groupProject.ANPRAPI.Domain.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    void initialiseUser(User user, String password) throws ParseException;

    List<User> findAll();

    User findUser(String userName);

    void update(User user);

    Boolean findUserForNumberPlate(String numberPlate);

    Integer getUserGroup(String userName);

    User findUserByID(Integer ID);

    void addCredits(Integer credits, String email);

    void deductCredits(Integer userID);


}
