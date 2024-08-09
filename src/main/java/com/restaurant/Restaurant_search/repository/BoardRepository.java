package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // 기본 CRUD 메소드가 자동 제공
}
