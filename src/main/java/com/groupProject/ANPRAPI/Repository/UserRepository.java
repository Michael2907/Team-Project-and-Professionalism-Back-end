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
    @Query(value = "INSERT INTO USER_TABLE (userName, password, numberPlate, userGroup, email, startDateTime, endDateTime) VALUES (:userName, :password, :numberPlate, :userGroup, :email, :sqlStartDate, :sqlEndDate)", nativeQuery = true)
    void save(@Param("userName") String userName, @Param("password") String password,
              @Param("numberPlate") String numberPlate, @Param("userGroup") Integer userGroup,
              @Param("email") String email, @Param("sqlStartDate") java.sql.Date sqlStartDate,
              @Param("sqlEndDate") java.sql.Date sqlEndDate);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USER_TABLE (userName, password, numberPlate, userGroup, email) VALUES (:userName, :password, :numberPlate, :userGroup, :email)", nativeQuery = true)
    void saveNonGuest(@Param("userName") String userName, @Param("password") String password,
              @Param("numberPlate") String numberPlate, @Param("userGroup") Integer userGroup,
              @Param("email") String email);

    @Query(value = "SELECT * FROM USER_TABLE WHERE numberPlate = :numberPlate", nativeQuery = true)
    User find(@Param("numberPlate") String numberPlate);

    @Query(value = "SELECT * FROM USER_TABLE WHERE userName = :userName", nativeQuery = true)
    User findUser(@Param("userName") String userName);

    @Query(value = "SELECT * FROM USER_TABLE WHERE userID = :userID", nativeQuery = true)
    User findUserByID(@Param("userID") Integer userID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USER_TABLE set credits = credits + :credits where email = :email", nativeQuery = true)
    void addCredits(@Param("credits") Integer credits, @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USER_TABLE set credits = credits - 1 where userID = :userID", nativeQuery = true)
    void deductCredits(@Param("userID") Integer userID);
}