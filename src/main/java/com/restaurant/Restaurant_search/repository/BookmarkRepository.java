package com.restaurant.Restaurant_search.repository;

import com.restaurant.Restaurant_search.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository  extends JpaRepository<Bookmark, Long> {
}
