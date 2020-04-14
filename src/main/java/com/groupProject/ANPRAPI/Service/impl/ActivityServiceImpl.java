package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Repository.ActivityRepository;
import com.groupProject.ANPRAPI.Repository.UserRepository;
import com.groupProject.ANPRAPI.Service.ActivityService;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }

    @Override
    public void logActivity(String numberPlate) {
        User user = userRepository.find(numberPlate);
        if(user != null){
            Activity foundActivity = this.activityRepository.findLatestActivity(user.getUserId());

            if(foundActivity != null){
                Calendar currenttime = Calendar.getInstance();
                java.sql.Timestamp sqldate = new java.sql.Timestamp((currenttime.getTime()).getTime());
                this.activityRepository.update(sqldate, foundActivity.getActivityID());
            }else{
                Activity activity = new Activity();
                activity.setUserID(user.getUserId());
                Calendar currenttime = Calendar.getInstance();
                java.sql.Timestamp sqldate = new java.sql.Timestamp((currenttime.getTime()).getTime());
                this.activityRepository.save(sqldate, activity.getUserID());
            }
        }
    }

    @Override
    public List<Activity> findAllForDates(java.sql.Date startDate, java.sql.Date endDate) {
        return this.activityRepository.findAllForDates(startDate, endDate);
    }

    @Override
    public List<Activity> findAllCurrentlyParked() {
        return this.activityRepository.findAllCurrentlyParked();
    }
}
