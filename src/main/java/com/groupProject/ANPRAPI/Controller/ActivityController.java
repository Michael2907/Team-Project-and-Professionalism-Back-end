package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserActivity;
import com.groupProject.ANPRAPI.Service.ActivityService;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import com.groupProject.ANPRAPI.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags="Activity Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlacklistService blacklistService;

    @GetMapping("")
    private List<Activity> getActivity(){
        return activityService.findAll();
    }

    @GetMapping("/date")
    private List<UserActivity> getActivityForDay(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        Date formattedStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date formattedEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        java.sql.Date sqlStartDate = new java.sql.Date(formattedStartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(formattedEndDate.getTime());

        List<UserActivity> userActivityList = new ArrayList();
        List<Activity> activities = activityService.findAllForDates(sqlStartDate, sqlEndDate);
        return getUserActivities(userActivityList, activities);
    }

    private List<UserActivity> getUserActivities(List<UserActivity> userActivityList, List<Activity> activities) {
        for(Activity activity: activities){
            UserActivity userActivity = new UserActivity();
            userActivity.setActivity(activity);
            User user = new User();
            user = userService.findUserByID(activity.getUserID());
            user.setPassword(null);
            userActivity.setUser(user);
            userActivityList.add(userActivity);
        }
        return userActivityList;
    }

    @GetMapping("/currentlyParked")
    private List<UserActivity> getCurrentlyParked(){
        List<UserActivity> userActivityList = new ArrayList();
        List<Activity> activities = activityService.findAllCurrentlyParked();
        return getUserActivities(userActivityList, activities);
    }

    @PostMapping("")
    private ResponseEntity<?> postActivity(@RequestParam String numberPlate){
        if(userService.findUserForNumberPlate(numberPlate)){
            Boolean blacklistUser = blacklistService.findForNumberPlate(numberPlate);
            User user = userService.findUserForNumberPlateUser(numberPlate);
            if(blacklistUser){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Date date = new Date();
            if(user.getUserGroup() == 3 && date.after(user.getEndDateTime())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            activityService.logActivity(numberPlate);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
