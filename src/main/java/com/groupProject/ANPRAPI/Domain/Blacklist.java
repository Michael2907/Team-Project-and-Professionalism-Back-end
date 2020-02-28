package com.groupProject.ANPRAPI.Domain;

import javax.persistence.*;

@Entity
@Table(name = "Blacklist_Table")
@IdClass(BlacklistPK.class)
public class Blacklist {

    @Id
    @Column(name = "NumberPlate")
    private String numberPlate;

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberPlate(){
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
}
