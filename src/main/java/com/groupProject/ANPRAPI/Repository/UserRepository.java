package com.groupProject.ANPRAPI.Repository;

import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, UserPK>, JpaSpecificationExecutor<User> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USER_TABLE VALUES (:userName, :password, :numberPlate, :userGroup, :email)", nativeQuery = true)
    void save(@Param("userName") String userName, @Param("password") String password,
              @Param("numberPlate") String numberPlate, @Param("userGroup") Integer userGroup,
              @Param("email") String email);
}