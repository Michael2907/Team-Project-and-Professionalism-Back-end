package com.groupProject.ANPRAPI.Domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Log_Table")
@IdClass(EventLogPK.class)
public class EventLog {

    @Id
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "dateTime")
    private java.sql.Timestamp dateTime;

    @Column(name = "description")
    private String description;

    public Integer getUserID(){
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public java.sql.Timestamp getDateTime(){
        return dateTime;
    }

    public void setDateTime(java.sql.Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
