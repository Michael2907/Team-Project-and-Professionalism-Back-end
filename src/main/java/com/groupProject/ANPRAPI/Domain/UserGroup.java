package com.groupProject.ANPRAPI.Domain;

import javax.persistence.*;

@Entity
@Table(name = "UserGroup_Table")
@IdClass(UserGroupPK.class)
public class UserGroup {

    @Id
    @Column(name = "UserGroupID")
    private Integer userGroupID;

    @Column(name = "name")
    private String name;

    public Integer getUserGroupID(){
        return userGroupID;
    }

    public void setUserGroupID(Integer userGroupID) {
        this.userGroupID = userGroupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
