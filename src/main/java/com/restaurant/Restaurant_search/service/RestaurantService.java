package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> searchRestaurants(String name, String address, String phoneNumber, String menu) {
        if (name != null && !name.isEmpty()) {
            return restaurantRepository.findByRestaurantNameContainingIgnoreCase(name);
        } else if (address != null && !address.isEmpty()) {
            return restaurantRepository.findByAddressContainingIgnoreCase(address);
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return restaurantRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber);
        } else if (menu != null && !menu.isEmpty()) {
            return restaurantRepository.findByMenuContainingIgnoreCase(menu);
        } else {
            return restaurantRepository.findAll(); // 기본적으로 모든 레코드를 반환
        }
    }

    // 자동 완성 제안 기능을 위한 메서드
    public List<String> getAutoCompleteSuggestions(String query) {
        List<Restaurant> restaurants = restaurantRepository.findByRestaurantNameContainingIgnoreCase(query);
        return restaurants.stream()
                .map(Restaurant::getRestaurantName)
                .distinct()
                .collect(Collectors.toList());
    }
}
