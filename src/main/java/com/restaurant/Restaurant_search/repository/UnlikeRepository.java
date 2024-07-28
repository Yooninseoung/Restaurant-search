package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.UnLikeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnlikeRepository extends JpaRepository<UnLikeData, Long> {

    @Query("SELECT COUNT(e) FROM UnLikeData e WHERE e.commonDataID.restaurantID = :restaurantId")
    int countByRestaurantId(@Param("restaurantId") Integer restaurantId);
}
