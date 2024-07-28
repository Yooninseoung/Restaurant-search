package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.CommonDataID;
import com.restaurant.Restaurant_search.entity.LikeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//좋아요와 싫어요 공통 처리
public interface LikeRepository extends JpaRepository<LikeData, Long> {

    @Query("SELECT COUNT(e) FROM LikeData e WHERE e.commonDataID.restaurantID = :restaurantId")
    int countByRestaurantId(@Param("restaurantId") Integer restaurantId);

}
