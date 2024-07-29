package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "UserReaction")
@NoArgsConstructor
public class UserReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantID", nullable = false)
    private Restaurant restaurant;

    @Column(name = "liked", nullable = false)
    private boolean liked;

    @Column(name = "disliked", nullable = false)
    private boolean disliked;

    @Column(name = "bookmarked", nullable = false)
    private boolean bookmarked;

    public UserReaction(User user, Restaurant restaurant, boolean liked, boolean disliked, boolean bookmarked) {
        this.user = user;
        this.restaurant = restaurant;
        this.liked = liked;
        this.disliked = disliked;
        this.bookmarked = bookmarked;
    }
}
