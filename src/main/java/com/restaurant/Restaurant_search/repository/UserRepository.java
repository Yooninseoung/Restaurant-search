package com.restaurant.Restaurant_search.repository;


import com.restaurant.Restaurant_search.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
