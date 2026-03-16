package com.routeplanner.smartrouteplanner.model;

import jakarta.persistence.*;

@Entity
public class BucketList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String place;

    private String userEmail;

    public BucketList(){}

    public Long getId(){ return id; }

    public String getPlace(){ return place; }

    public void setPlace(String place){ this.place = place; }

    public String getUserEmail(){ return userEmail; }

    public void setUserEmail(String userEmail){ this.userEmail = userEmail; }
}