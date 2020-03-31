package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.Blacklist;
import com.groupProject.ANPRAPI.Repository.ActivityRepository;
import com.groupProject.ANPRAPI.Repository.BlacklistRepository;
import com.groupProject.ANPRAPI.Service.ActivityService;
import com.groupProject.ANPRAPI.Service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public List<Blacklist> findAll() {
        return this.blacklistRepository.findAll();
    }

    @Override
    public void delete(String numberPlate) {
        this.blacklistRepository.delete(numberPlate);
    }

    @Override
    public void save(Blacklist blacklist) {
        this.blacklistRepository.save(blacklist);
    }
}
