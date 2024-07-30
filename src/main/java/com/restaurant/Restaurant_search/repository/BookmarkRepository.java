package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository  extends JpaRepository<Bookmark, Long> {

    @Query("SELECT COUNT(e) FROM Bookmark e WHERE e.commonDataID.restaurantID = :restaurantId")
    int countByRestaurantId(@Param("restaurantId") Integer restaurantId);


    @Query("SELECT COUNT(l) > 0 FROM Bookmark l WHERE l.commonDataID.restaurantID = :restaurantId AND l.commonDataID.userID = :userId")
    Boolean existsByRestaurantIdAndUserId(@Param("restaurantId") Integer restaurantId, @Param("userId") String userId);
}
