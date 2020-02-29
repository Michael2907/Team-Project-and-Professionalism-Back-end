package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.Blacklist;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="Blacklist Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    @GetMapping("")
    private List<Blacklist> getBlacklist(){
        return blacklistService.findAll();
    }
}
