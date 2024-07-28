package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class LikeData {
    @EmbeddedId
    CommonDataID commonDataID;

    public LikeData() {
    }

    public LikeData(CommonDataID id) {
        this.commonDataID = id;
    }

}
