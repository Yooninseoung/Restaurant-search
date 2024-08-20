package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Integer>{

    @Query("SELECT COUNT(e) FROM BoardLike e WHERE e.commonBoardLikeID.boardId = :boardId")
    int countByBoardId(@Param("boardId") Integer boardId);


    @Query("SELECT COUNT(l) > 0 FROM BoardLike l WHERE l.commonBoardLikeID.boardId = :boardId AND l.commonBoardLikeID.userID = :userId")
    Boolean existsByBoardIdAndUserId(@Param("boardId") Integer boardId, @Param("userId") String userId);
}
