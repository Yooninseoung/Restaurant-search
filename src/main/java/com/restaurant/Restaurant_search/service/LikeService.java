package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.CommonDataID;
import com.restaurant.Restaurant_search.entity.LikeData;
import com.restaurant.Restaurant_search.entity.UnLikeData;
import com.restaurant.Restaurant_search.repository.LikeRepository;
import com.restaurant.Restaurant_search.repository.UnlikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UnlikeRepository unlikeRepository;

    public void addLike(Integer restaurantId, String userId){
        CommonDataID like = new CommonDataID(userId, restaurantId);
        likeRepository.save(new LikeData(like));

    }

    public int countLike(Integer restaurantId){

        return likeRepository.countByRestaurantId(restaurantId);
    }

    public void addUnLike(Integer restaurantId, String userId){
        CommonDataID like = new CommonDataID(userId, restaurantId);
        unlikeRepository.save(new UnLikeData(like));

    }

    public int countUnLike(Integer restaurantId){

        return unlikeRepository.countByRestaurantId(restaurantId);
    }
}
