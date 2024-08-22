package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "boardlike")

public class BoardLike {

    @EmbeddedId
    private CommonBoardLikeID commonBoardLikeID;

    @Column(name = "likes")
    private Integer likes;

    // 기본 생성자
    public BoardLike() {
    }

    public BoardLike(CommonBoardLikeID commonBoardLikeID, Integer likes) {
        this.commonBoardLikeID = commonBoardLikeID;
        this.likes = likes;
    }

    public CommonBoardLikeID getCommonBoardLikeID() {
        return commonBoardLikeID;
    }

    public void setCommonBoardLikeID(CommonBoardLikeID commonBoardLikeID) {
        this.commonBoardLikeID = commonBoardLikeID;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
