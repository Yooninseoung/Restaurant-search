package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 필요한 커스텀 메소드 정의
}
