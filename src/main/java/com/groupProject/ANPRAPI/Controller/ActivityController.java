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

/**
 * REST Controller that handle all endpoints relating to the user activity
 */
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

    /**
     * GET request to return all activity in the car park
     * @return List of actvity objects
     */
    @GetMapping("")
    private List<Activity> getActivity(){
        return activityService.findAll();
    }

    /**
     * GET request to return all activity in the car park between two dates
     * @param startDate
     * @param endDate
     * @return List of activities with their associated user object
     * @throws ParseException
     */
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

    /**
     * Function to return a list of users for each activity provided
     * @param userActivityList
     * @param activities
     * @return List of users with their activites
     */
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

    /**
     * GET request to return who is currently parked in the car park
     * @return List of users and activity objects
     */
    @GetMapping("/currentlyParked")
    private List<UserActivity> getCurrentlyParked(){
        List<UserActivity> userActivityList = new ArrayList();
        List<Activity> activities = activityService.findAllCurrentlyParked();
        return getUserActivities(userActivityList, activities);
    }

    /**
     * Post request that takes in a numberplate and processes the user to log this activity and decide if they are to be
     * permitted into the car park
     * @param numberPlate
     * @return Response entity with appropriate response code
     */
    @PostMapping("")
    private ResponseEntity<?> postActivity(@RequestParam String numberPlate){
        if(userService.findUserForNumberPlate(numberPlate)){
            Boolean blacklistUser = blacklistService.findForNumberPlate(numberPlate);
            User user = userService.findUserForNumberPlateUser(numberPlate);
            if(blacklistUser){
                //If user has been blacklisted
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Date date = new Date();
            if(user.getUserGroup() == 3 && date.after(user.getEndDateTime())){
                //If user is a guest and their expiry date has passed
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            //Log user activity and return 200 response code to indicate to let them into the car park
            activityService.logActivity(numberPlate);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            //User not found in the system
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
