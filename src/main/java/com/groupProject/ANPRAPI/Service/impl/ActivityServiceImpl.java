package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Repository.ActivityRepository;
import com.groupProject.ANPRAPI.Repository.UserRepository;
import com.groupProject.ANPRAPI.Service.ActivityService;
import com.groupProject.ANPRAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
                Date date = new Date();
                foundActivity.setDateTimeExited(date);
                this.activityRepository.save(foundActivity);
            }else{
                Activity activity = new Activity();
                activity.setUserID(user.getUserId());
                Date date = new Date();
                activity.setDateTimeEntered(date);
                this.activityRepository.save(activity);
            }
        }
    }
}
