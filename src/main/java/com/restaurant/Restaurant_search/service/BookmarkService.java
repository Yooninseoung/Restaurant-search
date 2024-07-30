package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Bookmark;
import com.restaurant.Restaurant_search.entity.CommonDataID;
import com.restaurant.Restaurant_search.entity.LikeData;
import com.restaurant.Restaurant_search.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {
    @Autowired
    BookmarkRepository bookmarkRepository;

    public void addBookmark(Bookmark bookmark){
        bookmarkRepository.save(bookmark);

    }

    public void deleteBookmark(Bookmark bookmark){
        bookmarkRepository.delete(bookmark);

    }

    public boolean existBookmrk(Integer restaurantId, String userId){ // 북마크가 존재한다면 true
        return bookmarkRepository.existsByRestaurantIdAndUserId(restaurantId, userId);
    }
}
