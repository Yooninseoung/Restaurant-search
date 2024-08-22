package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Report;
import com.restaurant.Restaurant_search.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("SELECT COUNT(e) FROM Report e WHERE e.commonReportID.boardId = :boardId")
    int countByBoardID(@Param("boardId") Integer boardId);


    @Query("SELECT COUNT(l) > 0 FROM Report l WHERE l.commonReportID.boardId = :boardId AND l.commonReportID.userID = :userId")
    Boolean existsByBoardIdAndUserId(@Param("boardId") Integer boardId, @Param("userId") String userId);
}
