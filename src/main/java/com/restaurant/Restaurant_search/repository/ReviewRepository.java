package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantId(int restaurantId);
    Page<Review> findAllByRestaurantId(int restaurantId, Pageable pageable); //페이징 기능
}
