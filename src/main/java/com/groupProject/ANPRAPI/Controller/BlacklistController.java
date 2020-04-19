package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.Blacklist;
import com.groupProject.ANPRAPI.Domain.EventLog;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import com.groupProject.ANPRAPI.Service.EventLogService;
import com.groupProject.ANPRAPI.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Calendar;
import java.util.List;

/**
 * REST Controller that handles all endpoints relating to the blacklist
 */
@Api(tags="Blacklist Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventLogService eventLogService;

    /**
     * GET request to return a list of blacklist objects
     * @return Blacklist objects
     */
    @GetMapping("")
    private List<Blacklist> getBlacklist(){
        return blacklistService.findAll();
    }

    /**
     * Delete request to remove a user from the blacklist
     * @param numberPlate
     * @param userID
     * @return String with success/fail message
     * @throws Exception
     */
    @DeleteMapping("")
    private String deleteFromBlacklist(@RequestParam String numberPlate, @RequestParam Integer userID) throws Exception {
        try{
            blacklistService.delete(numberPlate);
            User user = userService.findUserForNumberPlateUser(numberPlate);
            //Log action in log table
            EventLog eventLog = new EventLog();
            eventLog.setUserID(userID);
            eventLog.setDescription("User: " + user.getUsername() + " removed from blacklist by user: " + userID);
            Calendar currentTime = Calendar.getInstance();
            java.sql.Timestamp sqldate = new java.sql.Timestamp((currentTime.getTime()).getTime());
            eventLog.setDateTime(sqldate);
            eventLogService.save(eventLog);
        }catch(Exception ex){
            throw new Exception("Number plate not found");
        }
        return "Success";
    }

    /**
     * PUT request to add users to the blacklist
     * @param blacklist
     * @param userID
     */
    @PutMapping("")
    private void insertIntoBlacklist(@RequestBody Blacklist blacklist, @RequestParam Integer userID){
        blacklistService.save(blacklist);
        User user = userService.findUserForNumberPlateUser(blacklist.getNumberPlate());
        //Log activity in the event log table
        EventLog eventLog = new EventLog();
        eventLog.setUserID(userID);
        eventLog.setDescription("User: " + user.getUsername() + " added to blacklist by user: " + userID);
        Calendar currentTime = Calendar.getInstance();
        java.sql.Timestamp sqldate = new java.sql.Timestamp((currentTime.getTime()).getTime());
        eventLog.setDateTime(sqldate);
        eventLogService.save(eventLog);
    }
}
