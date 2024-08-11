package com.restaurant.Restaurant_search.repository;


import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT r FROM User r WHERE r.userID LIKE %:searchId%")
    List<User> searchByUserId(@Param("searchId") String searchId);
}
