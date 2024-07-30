package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> restaurantList(){ //index화면에 보이는 9개의 식당 정보를 반환
        List<Restaurant> list = restaurantRepository.findTop9ByRating();
        return list;
    }

    public List<Restaurant> rankRestaurantList(){ //index화면에 보이는 20개의 식당 정보를 반환
        List<Restaurant> list = restaurantRepository.findTop21ByRating();
        return list;
    }

    public Restaurant findRestaurant(long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElse(null); // 식당이 존재하지 않으면 null을 반환하거나 예외를 던질 수 있음

    }


    public void avgRestaurantRating(BigDecimal reviewRating, int id){ //리뷰로 받은 평점과 기본 평점의 평균을 구해 다시 저장
        Restaurant restaurant = findRestaurant(id);
        BigDecimal restaurantRating = restaurant.getRating();
        BigDecimal rating;

        if(restaurantRating == null){ //기존 식당 평점이 존재하지 않으면 새로받은 평점을 넣음
            restaurant.setRating(reviewRating);

        }else{ //기존 평점이 존재하면 평균을 구함
            rating = restaurantRating.add(reviewRating);
            rating = rating.divide(new BigDecimal("2"));
            restaurant.setRating(rating);
        }
        restaurantRepository.save(restaurant);
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> searchRestaurants(String searchName) {
        List<Restaurant> restaurants = restaurantRepository.searchByRestaurantName(searchName);

        if (restaurants.isEmpty()) {
            return null;
        }
        return restaurants;
    }


}
