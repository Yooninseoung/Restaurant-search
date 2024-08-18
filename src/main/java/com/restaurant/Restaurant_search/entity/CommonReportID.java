package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;

import java.io.Serializable;

//복합키를 표현하기 위해 제작

@Embeddable
@Table(name = "Report")
public class CommonReportID implements Serializable {

    @Column(name = "userID")
    private String userID;

    @Column(name = "boardId")
    private Integer boardId;

    // Constructors
    public CommonReportID() {
    }

    public CommonReportID(String userID, Integer boardId) {
        this.userID = userID;
        this.boardId = boardId;

    }

    // Getters and Setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }




}