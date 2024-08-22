package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Bookmark;
import com.restaurant.Restaurant_search.entity.Report;
import com.restaurant.Restaurant_search.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public void addReport(Report report) {
        reportRepository.save(report);

    }

    public void deleteReport(Report report) {
        reportRepository.delete(report);

    }

    public boolean existReport(Report report) { // 북마크가 존재한다면 true
        return reportRepository.existsByBoardIdAndUserId(report.getCommonReportID().getBoardId(), report.getCommonReportID().getUserID());
    }

    public int countReport(Integer boardId) {
        return reportRepository.countByBoardID(boardId);
    }
}
