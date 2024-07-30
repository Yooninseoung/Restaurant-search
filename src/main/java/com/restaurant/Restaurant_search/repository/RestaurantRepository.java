package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM Restaurant r ORDER BY r.rating DESC LIMIT 9", nativeQuery = true)
    List<Restaurant> findTop9ByRating();
}
