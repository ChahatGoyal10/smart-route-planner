package com.routeplanner.smartrouteplanner.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewText;

    private int rating;

    private String userEmail;

    public Review(){}

    public Long getId(){ return id; }

    public String getReviewText(){ return reviewText; }

    public void setReviewText(String reviewText){ this.reviewText = reviewText; }

    public int getRating(){ return rating; }

    public void setRating(int rating){ this.rating = rating; }

    public String getUserEmail(){ return userEmail; }

    public void setUserEmail(String userEmail){ this.userEmail = userEmail; }
}