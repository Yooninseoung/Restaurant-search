package com.restaurant.Restaurant_search.repository;


import com.restaurant.Restaurant_search.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

    // 사용자 이름으로 사용자 조회 메서드 추가
    User findByUsername(String username);

}
