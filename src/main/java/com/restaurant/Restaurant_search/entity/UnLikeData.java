package com.restaurant.Restaurant_search.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UnLikeData {
    @EmbeddedId
    CommonDataID commonDataID;

    public UnLikeData() {
    }

    public UnLikeData(CommonDataID id) {
        this.commonDataID = id;
    }
}
