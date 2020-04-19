package com.groupProject.ANPRAPI.Service;

import com.groupProject.ANPRAPI.Domain.EventLog;

import java.util.List;

public interface EventLogService {

    List<EventLog> findAll();

    void save(EventLog eventLog);
}
