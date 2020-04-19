package com.groupProject.ANPRAPI.Repository;

import com.groupProject.ANPRAPI.Domain.EventLog;
import com.groupProject.ANPRAPI.Domain.EventLogPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventLogRepository extends JpaRepository<EventLog, EventLogPK>, JpaSpecificationExecutor<EventLog> {

}