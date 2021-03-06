package com.groupProject.ANPRAPI.Repository;

import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserGroup;
import com.groupProject.ANPRAPI.Domain.UserGroupPK;
import com.groupProject.ANPRAPI.Domain.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupPK>, JpaSpecificationExecutor<UserGroup> {

}