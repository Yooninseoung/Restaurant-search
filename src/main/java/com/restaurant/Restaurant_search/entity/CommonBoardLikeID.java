package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;

import java.io.Serializable;

//복합키를 표현하기 위해 제작

@Embeddable
public class CommonBoardLikeID implements Serializable {

    @Column(name = "userID")
    private String userID;

    @Column(name = "board_id")
    private Integer boardId;


    // Constructors
    public CommonBoardLikeID() {
    }

    public CommonBoardLikeID(String userID, Integer boardId) {
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