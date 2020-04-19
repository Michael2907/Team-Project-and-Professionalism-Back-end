package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Repository.UserRepository;
import com.groupProject.ANPRAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialiseUser(User user, String password) throws ParseException {

        if(user.getStartDateTime() != null && user.getEndDateTime() != null){
            java.sql.Date sqlStartDate = convertUtilToSql(user.getStartDateTime());
            java.sql.Date sqlEndDate = convertUtilToSql(user.getEndDateTime());
            this.userRepository.save(user.getUsername(), password, user.getNumberPlate(), user.getUserGroup(), user.getEmail(), sqlStartDate, sqlEndDate);
        }else{
            this.userRepository.saveNonGuest(user.getUsername(), password, user.getNumberPlate(), user.getUserGroup(), user.getEmail());
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findUser(String userName) {
        return this.userRepository.findUser(userName);
    }

    @Override
    public void update(User user) {
        this.userRepository.save(user);
    }

    @Override
    public Boolean findUserForNumberPlate(String numberPlate) {
        User foundUser = this.userRepository.find(numberPlate);
        if(foundUser != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User findUserForNumberPlateUser(String numberPlate) {
        return this.userRepository.findAllForNumberPlate(numberPlate);
    }

    @Override
    public Integer getUserGroup(String userName) {
        return userRepository.findUser(userName).getUserGroup();
    }

    @Override
    public User findUserByID(Integer ID) {
        return userRepository.findUserByID(ID);
    }

    @Override
    public void addCredits(Integer credits, String email) {
        this.userRepository.addCredits(credits, email);
    }

    @Override
    public void deductCredits(Integer userID) {
        this.userRepository.deductCredits(userID);
    }
}
