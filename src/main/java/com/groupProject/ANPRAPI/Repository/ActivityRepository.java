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

public interface ActivityRepository extends JpaRepository<Activity, ActivityPK>, JpaSpecificationExecutor<Activity> {

    @Query(value = "SELECT * FROM ACTIVITY_TABLE WHERE USERID = :userId and DATETIMEEXITED is null", nativeQuery = true)
    Activity findLatestActivity(@Param("userId") Integer userId);
}