package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Bookmark;
import com.restaurant.Restaurant_search.entity.CommonDataID;
import com.restaurant.Restaurant_search.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {
    @Autowired
    BookmarkRepository bookmarkRepository;

    public void addBookmark(Integer restaurantId, String userId){
        CommonDataID bookmark = new CommonDataID(userId, restaurantId);
        bookmarkRepository.save(new Bookmark(bookmark));

    }

    public void deleteBookmark(Integer restaurantId, String userId){
        CommonDataID bookmark = new CommonDataID(userId, restaurantId);
        bookmarkRepository.delete(new Bookmark(bookmark));

    }
}
