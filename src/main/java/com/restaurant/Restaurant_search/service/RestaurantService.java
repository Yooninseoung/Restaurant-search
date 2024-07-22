package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant findRestaurant(long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElse(null); // 식당이 존재하지 않으면 null을 반환하거나 예외를 던질 수 있음

    }
}
