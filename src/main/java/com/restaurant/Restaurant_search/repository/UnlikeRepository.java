package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.UnLikeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnlikeRepository extends JpaRepository<UnLikeData, Long> {


    //싫어요 수
    @Query("SELECT COUNT(e) FROM UnLikeData e WHERE e.commonDataID.restaurantID = :restaurantId")
    int countByRestaurantId(@Param("restaurantId") Integer restaurantId);

    //싫어요 데이터가 존재?
    @Query("SELECT COUNT(l) > 0 FROM UnLikeData l WHERE l.commonDataID.restaurantID = :restaurantId AND l.commonDataID.userID = :userId")
    Boolean existsByRestaurantIdAndUserId(@Param("restaurantId") Integer restaurantId, @Param("userId") String userId);

}
