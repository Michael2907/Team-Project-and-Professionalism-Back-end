package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.Blacklist;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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

    @DeleteMapping("")
    private String deleteFromBlacklist(@RequestParam String numberPlate) throws Exception {
        try{
            blacklistService.delete(numberPlate);
        }catch(Exception ex){
            throw new Exception("Number plate not found");
        }
        return "Success";
    }

    @PutMapping("")
    private void insertIntoBlacklist(@RequestBody Blacklist blacklist){
        blacklistService.save(blacklist);
    }
}
