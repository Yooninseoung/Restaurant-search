package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Restaurant( String restaurantName, String phoneNumber, String address, String menu) {

        this.restaurantName = restaurantName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.menu = menu;
    }

    // Getters and Setters
}
