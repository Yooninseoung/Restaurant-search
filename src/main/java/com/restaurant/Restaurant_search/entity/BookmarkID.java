package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;

import java.io.Serializable;

//복합키를 표현하기 위해 제작

@Embeddable
@Table(name = "Bookmark")
public class BookmarkID implements Serializable {

    private String userID;


    private Integer restaurantID;

    // Constructors
    public BookmarkID() {
    }

    public BookmarkID(String userID, Integer restaurantID) {
        this.userID = userID;
        this.restaurantID = restaurantID;
    }

    // Getters and Setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }



}