package com.groupProject.ANPRAPI.Domain;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import javax.persistence.*;

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

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.email = "test@test.com";
        this.numberPlate = "sgsfsf";
        this.userGroup = 1;
    }

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
}
