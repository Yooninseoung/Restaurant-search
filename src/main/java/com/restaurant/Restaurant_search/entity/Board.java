package com.restaurant.Restaurant_search.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardID")
    private Integer boardId; // 게시글 고유 번호

    @Column(name = "title", nullable = false)
    private String title; // 제목

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content; // 글내용

    @Column(name = "username", nullable = false)
    private String username; // 글쓴이 (user 테이블의 username과 연관)

    @Column(name = "write_date", nullable = false, updatable = false)
    private LocalDateTime writeDate; // 작성일

    @Column(name = "likes", nullable = true)
    private Integer likes; // 좋아요 수, NULL 가능

    @PrePersist
    protected void onCreate() {
        this.writeDate = LocalDateTime.now();
    }

    // 기본 생성자
    public Board() {
    }

    // 생성자
    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.writeDate = LocalDateTime.now(); // 현재 시간으로 설정
    }

    // Getter 및 Setter 메소드
    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }

    public Integer getLikes() { return likes; }

    public void setLikes(Integer likes) { this.likes = likes; }
}
