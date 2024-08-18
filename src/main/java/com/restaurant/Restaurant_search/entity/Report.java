package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "report")

public class Report {

    @EmbeddedId
    CommonReportID commonReportID;

    // 기본 생성자
    public Report() {
    }

    public Report(CommonReportID commonReportID) {
        this.commonReportID = commonReportID;
    }

    public CommonReportID getCommonReportID() {
        return commonReportID;
    }

    public void setCommonReportID(CommonReportID commonReportID) {
        this.commonReportID = commonReportID;
    }
}
