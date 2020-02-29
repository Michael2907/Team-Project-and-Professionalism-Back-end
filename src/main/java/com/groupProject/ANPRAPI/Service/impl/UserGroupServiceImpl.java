package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.UserGroup;
import com.groupProject.ANPRAPI.Repository.CarParkRepository;
import com.groupProject.ANPRAPI.Repository.UserGroupRepository;
import com.groupProject.ANPRAPI.Service.CarParkService;
import com.groupProject.ANPRAPI.Service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public List<UserGroup> findAll() {
        return userGroupRepository.findAll();
    }
}
