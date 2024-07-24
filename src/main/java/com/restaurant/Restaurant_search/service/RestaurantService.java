package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant findRestaurant(long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElse(null); // 식당이 존재하지 않으면 null을 반환하거나 예외를 던질 수 있음

    }

    public Restaurant like(Long id){
        // 해당 식당의 like 정보를 가져와 +1해준 후 업데이트
        Restaurant restaurant = findRestaurant(id);
        if (restaurant.getLikes() == null){
            restaurant.setLikes(0);
        }

        restaurant.setLikes(restaurant.getLikes()+1);

        restaurantRepository.save(restaurant); // save : 평소에는 insert, id가 존재하면 update 실행됨
        return restaurant;

    }


    public Restaurant dislike(Long id){
        // 해당 식당의 like 정보를 가져와 +1해준 후 업데이트
        Restaurant restaurant = findRestaurant(id);
        if (restaurant.getDislikes() == null){
            restaurant.setDislikes(0);
        }

        restaurant.setDislikes(restaurant.getDislikes()+1);

        restaurantRepository.save(restaurant); // save : 평소에는 insert, id가 존재하면 update 실행됨
        return restaurant;

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
}
