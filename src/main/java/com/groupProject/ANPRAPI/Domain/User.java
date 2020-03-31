package com.groupProject.ANPRAPI.Domain;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_Table")
@IdClass(UserPK.class)
public class User {

    @Id
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "numberPlate")
    private String numberPlate;

    @Column(name = "userGroup")
    private Integer userGroup;

    @Column(name = "email")
    private String email;

    @Column(name = "initialised")
    private Boolean initialised;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "startDateTime")
    private Date startDateTime;

    @Column(name = "endDateTime")
    private Date endDateTime;

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Integer getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Integer userGroup) {
        this.userGroup = userGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getInitialised() {
        return initialised;
    }

    public void setInitialised(Boolean initialised) {
        this.initialised = initialised;
    }

    public Boolean getDeleted(){
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getStartDateTime(){
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime(){
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
