package com.groupProject.ANPRAPI.Service;

import com.groupProject.ANPRAPI.Domain.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> findAll();

    void logActivity(String numberPlate);
}
