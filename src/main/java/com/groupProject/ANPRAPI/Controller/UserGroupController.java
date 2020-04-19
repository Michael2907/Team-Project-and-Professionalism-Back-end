package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.UserGroup;
import com.groupProject.ANPRAPI.Service.UserGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Generated;
import java.util.List;

/**
 * REST controller to handle interactions with the usergroup table
 */
@Api(tags="UserGroup Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    /**
     * GET request to get all usergroups
     * @return list of user group objects
     */
    @GetMapping("")
    private List<UserGroup> getUserGroups(){
        return userGroupService.findAll();
    }
}
