package com.routeplanner.smartrouteplanner.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RouteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromCity;
    private String toCity;
    private String routePath;
    private int totalCost;
    private LocalDateTime searchedAt;
    private String userEmail;

    public RouteHistory() {}

    public RouteHistory(String fromCity, String toCity, String userEmail, String routePath, int totalCost, LocalDateTime searchedAt) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.userEmail = userEmail;
        this.routePath = routePath;
        this.totalCost = totalCost;
        this.searchedAt = searchedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }
    public String getRoutePath() {
        return routePath;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getSearchedAt() {
        return searchedAt;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setSearchedAt(LocalDateTime searchedAt) {
        this.searchedAt = searchedAt;
    }
    public String getUserEmail() {   // ⭐ ADD
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
}
}