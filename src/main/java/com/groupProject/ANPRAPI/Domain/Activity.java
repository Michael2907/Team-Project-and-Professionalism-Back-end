package com.groupProject.ANPRAPI.Domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Activity_Table")
@IdClass(ActivityPK.class)
public class Activity {

    @Id
    @Column(name = "ActivityID")
    private Integer activityID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "DateTimeEntered")
    private Date dateTimeEntered;

    @Column(name = "DateTimeExited")
    private Date dateTimeExited;

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getDateTimeEntered() {
        return dateTimeEntered;
    }

    public void setDateTimeEntered(Date dateTimeEntered) {
        this.dateTimeEntered = dateTimeEntered;
    }

    public Date getDateTimeExited() {
        return dateTimeExited;
    }

    public void setDateTimeExited(Date dateTimeExited) {
        this.dateTimeExited = dateTimeExited;
    }
}
