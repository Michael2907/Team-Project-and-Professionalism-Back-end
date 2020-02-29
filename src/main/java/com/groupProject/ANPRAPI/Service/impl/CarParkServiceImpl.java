package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.CarPark;
import com.groupProject.ANPRAPI.Repository.BlacklistRepository;
import com.groupProject.ANPRAPI.Repository.CarParkRepository;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import com.groupProject.ANPRAPI.Service.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParkServiceImpl implements CarParkService {

    @Autowired
    private CarParkRepository carParkRepository;

    @Override
    public List<CarPark> findAll() {
        return this.carParkRepository.findAll();
    }
}
