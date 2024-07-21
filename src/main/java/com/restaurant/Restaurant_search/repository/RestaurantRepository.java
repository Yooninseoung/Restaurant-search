package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
