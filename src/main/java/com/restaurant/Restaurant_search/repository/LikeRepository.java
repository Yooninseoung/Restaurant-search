package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.CommonDataID;
import com.restaurant.Restaurant_search.entity.LikeData;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//좋아요와 싫어요 공통 처리
public interface LikeRepository extends JpaRepository<LikeData, Long> {

    //좋아요 수
    @Query("SELECT COUNT(e) FROM LikeData e WHERE e.commonDataID.restaurantID = :restaurantId")
    int countByRestaurantId(@Param("restaurantId") Integer restaurantId);

    //좋아요 데이터가 존재?
    @Query("SELECT COUNT(l) > 0 FROM LikeData l WHERE l.commonDataID.restaurantID = :restaurantId AND l.commonDataID.userID = :userId")
    Boolean existsByRestaurantIdAndUserId(@Param("restaurantId") Integer restaurantId, @Param("userId") String userId);


}
