package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Bookmark {
    @EmbeddedId
    CommonDataID commonDataID;

    public Bookmark() {
    }

    public Bookmark(CommonDataID id) {
        this.commonDataID = id;
    }
}
