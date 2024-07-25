package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Bookmark {
    @EmbeddedId
    BookmarkID bookmarkID;

    public Bookmark() {
    }

    public Bookmark(BookmarkID id) {
        this.bookmarkID = id;
    }
}
