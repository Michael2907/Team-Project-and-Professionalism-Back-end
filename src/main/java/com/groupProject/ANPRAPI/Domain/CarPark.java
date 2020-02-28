package com.groupProject.ANPRAPI.Domain;

import javax.persistence.*;

@Entity
@Table(name = "CarPark_Table")
@IdClass(CarParkPK.class)
public class CarPark {

    @Id
    @Column(name = "CarParkID")
    private Integer carParkID;

    @Column(name = "TotalSpaces")
    private String totalSpaces;

    public void setCarParkID(Integer carParkID) {
        this.carParkID = carParkID;
    }

    public Integer getCarParkID(){
        return carParkID;
    }

    public String getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(String totalSpaces) {
        this.totalSpaces = totalSpaces;
    }
}
