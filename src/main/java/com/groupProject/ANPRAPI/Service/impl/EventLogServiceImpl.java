package com.groupProject.ANPRAPI.Service.impl;

import com.groupProject.ANPRAPI.Domain.EventLog;
import com.groupProject.ANPRAPI.Repository.EventLogRepository;
import com.groupProject.ANPRAPI.Service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventLogServiceImpl implements EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;

    @Override
    public List<EventLog> findAll() {
        return this.eventLogRepository.findAll();
    }

    @Override
    public void save(EventLog eventLog) {
        this.eventLogRepository.save(eventLog);
    }
}
