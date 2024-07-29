package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.UserReaction;
import com.restaurant.Restaurant_search.repository.UserReactionRepository;
import com.restaurant.Restaurant_search.repository.UserRepository;
import com.restaurant.Restaurant_search.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReactionService {

    @Autowired
    private UserReactionRepository userReactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private void updateReaction(String userId, Integer restaurantId, boolean liked, boolean disliked, boolean bookmarked) {
        // 사용자와 레스토랑을 데이터베이스에서 조회
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        // 사용자의 레스토랑에 대한 기존 반응을 조회
        UserReaction reaction = userReactionRepository.findByUserAndRestaurant(user, restaurant);

        if (reaction == null) {
            // 기존 반응이 없다면 새로운 UserReaction 객체를 생성
            reaction = new UserReaction(user, restaurant, liked, disliked, bookmarked);
        } else {
            // 기존 반응이 있다면 상태를 업데이트
            reaction.setLiked(liked);
            reaction.setDisliked(disliked);
            reaction.setBookmarked(bookmarked);
        }
        // 변경된 반응을 저장
        userReactionRepository.save(reaction);

        // 레스토랑의 반응 수를 업데이트
        updateRestaurantCounts(restaurant);
    }

    private void updateRestaurantCounts(Restaurant restaurant) {
        // 좋아요, 싫어요, 즐겨찾기 수를 계산하여 업데이트
        int likes = userReactionRepository.countByRestaurantAndLiked(restaurant, true);
        int dislikes = userReactionRepository.countByRestaurantAndDisliked(restaurant, true);
        int bookmarks = userReactionRepository.countByRestaurantAndBookmarked(restaurant, true);

        restaurant.setLikes(likes);
        restaurant.setDislikes(dislikes);
        restaurant.setBookmarks(bookmarks);

        restaurantRepository.save(restaurant);
    }

    public void likeRestaurant(String userId, Integer restaurantId) {
        updateReaction(userId, restaurantId, true, false, false);
    }

    public void dislikeRestaurant(String userId, Integer restaurantId) {
        updateReaction(userId, restaurantId, false, true, false);
    }

    public void bookmarkRestaurant(String userId, Integer restaurantId) {
        updateReaction(userId, restaurantId, false, false, true);
    }
}
