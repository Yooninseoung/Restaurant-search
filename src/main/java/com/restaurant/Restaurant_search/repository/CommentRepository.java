package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByBoard(Board board);
    // 필요한 커스텀 메소드 정의
}
