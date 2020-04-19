package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Domain.EventLog;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserActivity;
import com.groupProject.ANPRAPI.Service.ActivityService;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import com.groupProject.ANPRAPI.Service.EventLogService;
import com.groupProject.ANPRAPI.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * REST controller to handle interactions with the event log table
 */
@Api(tags="EventLog Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/eventLog")
public class EventLogController {

    @Autowired
    private EventLogService eventLogService;

    /**
     * GET request to return all logs
     * @return List of event log objects
     */
    @GetMapping("")
    private List<EventLog> getEventLog(){
        return eventLogService.findAll();
    }

}
