package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByRestaurantNameContainingIgnoreCase(String restaurantName);
    List<Restaurant> findByAddressContainingIgnoreCase(String address);
    List<Restaurant> findByPhoneNumberContainingIgnoreCase(String phoneNumber);
    List<Restaurant> findByMenuContainingIgnoreCase(String menu);
}
