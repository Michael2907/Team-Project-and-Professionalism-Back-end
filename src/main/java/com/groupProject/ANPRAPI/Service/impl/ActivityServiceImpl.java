package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Repository.ActivityRepository;
import com.groupProject.ANPRAPI.Repository.UserRepository;
import com.groupProject.ANPRAPI.Service.ActivityService;
import com.groupProject.ANPRAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }
}
