package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM Restaurant r ORDER BY r.rating DESC LIMIT 9", nativeQuery = true)
    List<Restaurant> findTop9ByRating();

    @Query(value = "SELECT * FROM Restaurant r ORDER BY r.bookmarks DESC LIMIT 21", nativeQuery = true)
    List<Restaurant> findTop21ByRating();

    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName LIKE %:searchName%")
    List<Restaurant> searchByRestaurantName(@Param("searchName") String searchName);

}
