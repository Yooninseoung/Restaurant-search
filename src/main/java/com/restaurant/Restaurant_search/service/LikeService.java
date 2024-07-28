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

    public boolean existLikeData(Integer restaurantId, String userId){ // 좋아요가 존재한다면 true
        return likeRepository.existsByRestaurantIdAndUserId(restaurantId, userId);
    }

    public boolean existUnlikeData(Integer restaurantId, String userId){ // 싫어요가 존재한다면 true
        return unlikeRepository.existsByRestaurantIdAndUserId(restaurantId, userId);
    }


    public void addLike(LikeData likeData){ //좋아요 추가
        likeRepository.save(likeData);

    }

    public void deleteLike(LikeData likeData){ // 좋아요 취소
        likeRepository.delete(likeData);

    }

    public int countLike(Integer restaurantId){ // 좋아요 수

        return likeRepository.countByRestaurantId(restaurantId);
    }

    public void addUnLike(UnLikeData unlikeData){ //싫어요 추가
        unlikeRepository.save(unlikeData);

    }

    public void deleteUnLike(UnLikeData unlikeData){ // 싫어요 취소
        unlikeRepository.delete(unlikeData);

    }

    public int countUnLike(Integer restaurantId){ // 싫어요 수

        return unlikeRepository.countByRestaurantId(restaurantId);
    }
}
