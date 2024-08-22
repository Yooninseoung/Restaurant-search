package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private Integer reviewID;

    @JoinColumn(name = "restaurantId", nullable = false)
    private Integer restaurantId;

    @Column(name = "userID", nullable = false)
    private String userID;

    @Column(name = "OneLineReview", columnDefinition = "TEXT")
    private String OneLineReview;

    @Column(name = "rating", precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(name = "photo_path", length = 255)
    private String photo_path;

    // Getters and Setters
}
