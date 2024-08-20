package com.restaurant.Restaurant_search.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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

    @Column(name = "username", length = 50, nullable = false)
    private String username; // 글쓴이 (user 테이블의 username과 연관)

    @Column(name = "write_date", nullable = false, updatable = false)
    private LocalDateTime writeDate; // 작성일

    @Column(name = "boardlikes", nullable = true)
    private Integer boardlikes; // 좋아요 수, NULL 가능

    @Column(name = "reports", nullable = true)
    private Integer reports; // 신고하기 수, NULL 가능



    @Column(name = "photo_path", length = 255, nullable = true)
    private String photoPath; // 사진 경로

    // 댓글 목록 (게시글에 대한 댓글들)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

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
        this.photoPath = photoPath;
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

    public Integer getReports() { return reports; }

    public void setReports(Integer reports) { this.reports = reports; }

    public Integer getBoardLikes() { return boardlikes; }

    public void setBoardLikes(Integer boardlikes) { this.boardlikes = boardlikes; }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
