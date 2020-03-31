package com.groupProject.ANPRAPI.Repository;

import com.groupProject.ANPRAPI.Domain.Activity;
import com.groupProject.ANPRAPI.Domain.ActivityPK;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, ActivityPK>, JpaSpecificationExecutor<Activity> {

    @Query(value = "SELECT * FROM ACTIVITY_TABLE WHERE USERID = :userId and DATETIMEEXITED is null", nativeQuery = true)
    Activity findLatestActivity(@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM ACTIVITY_TABLE WHERE DateTimeEntered > :startDate and DateTimeExited < :endDate", nativeQuery = true)
    List<Activity> findAllForDates(@Param("startDate") java.sql.Date startDate, @Param("endDate") java.sql.Date endDate);

    @Query(value = "SELECT * FROM ACTIVITY_TABLE WHERE DateTimeExited is null", nativeQuery = true)
    List<Activity> findAllCurrentlyParked();
}