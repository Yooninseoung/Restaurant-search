package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @Column(name = "postID")
    private Integer postID;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "writeDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp writeDate;

    @Column(name = "likes")
    private Integer likes;

    // Getters and Setters
}
