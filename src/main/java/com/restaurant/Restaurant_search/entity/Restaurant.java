package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Restaurant")
public class Restaurant {

    @Id
    @Column(name = "restaurantID")
    private Integer restaurantID;

    @Column(name = "restaurant_name", length = 100)
    private String restaurantName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "opening_hours", length = 100)
    private String openingHours;

    @Column(name = "menu", columnDefinition = "TEXT")
    private String menu;

    @Column(name = "rating", precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name = "bookmarks")
    private Integer bookmarks;

    @Column(name = "photo_path", length = 200)
    private String photoPath;

    // Getters and Setters
}
