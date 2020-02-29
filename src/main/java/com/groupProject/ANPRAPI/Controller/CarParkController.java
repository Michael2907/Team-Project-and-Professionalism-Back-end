package com.groupProject.ANPRAPI.Controller;

import com.groupProject.ANPRAPI.Domain.CarPark;
import com.groupProject.ANPRAPI.Service.CarParkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="Carpark Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/carPark")
public class CarParkController {

    @Autowired
    private CarParkService carParkService;

    @GetMapping("")
    private List<CarPark> getCarPark(){
        return carParkService.findAll();
    }
}
