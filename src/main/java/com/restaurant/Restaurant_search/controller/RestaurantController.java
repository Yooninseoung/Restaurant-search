package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.service.UserReactionService;
import com.restaurant.Restaurant_search.service.UserIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RestaurantController는 레스토랑 관련 사용자 반응을 처리하는 RESTful API 엔드포인트를 제공
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private UserReactionService userReactionService; // UserReactionService를 주입받아 사용자 반응을 처리합니다.

    /**
     * 사용자가 레스토랑을 좋아요를 누를 때 호출되는 메서드입니다.
     * @param restaurantId 레스토랑의 고유 ID
     * @param userIdRequest 요청 본문에서 추출한 사용자 ID
     */
    @PostMapping("/{restaurantId}/like")
    public void likeRestaurant(@PathVariable Integer restaurantId, @RequestBody UserIdRequest userIdRequest) {
        userReactionService.likeRestaurant(userIdRequest.getUserId(), restaurantId); // 사용자 ID와 레스토랑 ID를 서비스 레이어로 전달하여 좋아요 처리
    }

    /**
     * 사용자가 레스토랑에 싫어요를 누를 때 호출되는 메서드입니다.
     */
    @PostMapping("/{restaurantId}/dislike")
    public void dislikeRestaurant(@PathVariable Integer restaurantId, @RequestBody UserIdRequest userIdRequest) {
        userReactionService.dislikeRestaurant(userIdRequest.getUserId(), restaurantId); // 사용자 ID와 레스토랑 ID를 서비스 레이어로 전달하여 싫어요 처리
    }

    /**
     * 사용자가 레스토랑을 즐겨찾기에 추가할 때 호출되는 메서드입니다.
     */
    @PostMapping("/{restaurantId}/bookmark")
    public void bookmarkRestaurant(@PathVariable Integer restaurantId, @RequestBody UserIdRequest userIdRequest) {
        userReactionService.bookmarkRestaurant(userIdRequest.getUserId(), restaurantId); // 사용자 ID와 레스토랑 ID를 서비스 레이어로 전달하여 즐겨찾기 처리
    }
}
