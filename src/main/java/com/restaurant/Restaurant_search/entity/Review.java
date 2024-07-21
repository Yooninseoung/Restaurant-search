package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reviewID")
    private Integer reviewID;

    @ManyToOne
    @JoinColumn(name = "restaurantID", nullable = false)
    private Restaurant restaurant;

    @Column(name = "userID", nullable = false)
    private Integer userID;

    @Column(name = "OneLineReview", columnDefinition = "TEXT")
    private String OneLineReview;

    @Column(name = "평점", precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(name = "photo_path", length = 255)
    private String  photo_path;

    // Getters and Setters
}
