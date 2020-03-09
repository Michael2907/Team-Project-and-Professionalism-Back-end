package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Service.ActivityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="Activity Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("")
    private List<Activity> getActivity(){
        return activityService.findAll();
    }

    @PostMapping("")
    private void postActivity(@RequestParam String numberPlate){
        activityService.logActivity(numberPlate);
    }
}
