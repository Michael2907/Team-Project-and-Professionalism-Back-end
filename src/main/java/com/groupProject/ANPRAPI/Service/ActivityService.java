package com.groupProject.ANPRAPI.Service;

import com.groupProject.ANPRAPI.Domain.Activity;

import java.sql.Date;
import java.util.List;

public interface ActivityService {

    List<Activity> findAll();

    void logActivity(String numberPlate);

    List<Activity> findAllForDates(Date startDate, Date endDate);

    List<Activity> findAllCurrentlyParked();
}
