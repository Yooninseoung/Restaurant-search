package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Bookmark;
import com.restaurant.Restaurant_search.entity.BookmarkID;
import com.restaurant.Restaurant_search.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {
    @Autowired
    BookmarkRepository bookmarkRepository;

    public void addBookmark(Integer restaurantId, String userId){
        BookmarkID bookmark = new BookmarkID(userId, restaurantId);
        bookmarkRepository.save(new Bookmark(bookmark));

    }
}
