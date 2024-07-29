package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.UserReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReactionRepository extends JpaRepository<UserReaction, Long> {

    // 사용자와 레스토랑에 대한 반응을 조회
    UserReaction findByUserAndRestaurant(User user, Restaurant restaurant);

    // 레스토랑에 대한 좋아요 수를 카운트
    int countByRestaurantAndLiked(Restaurant restaurant, boolean liked);

    // 레스토랑에 대한 싫어요 수를 카운트
    int countByRestaurantAndDisliked(Restaurant restaurant, boolean disliked);

    // 레스토랑에 대한 즐겨찾기 수를 카운트
    int countByRestaurantAndBookmarked(Restaurant restaurant, boolean bookmarked);
}
