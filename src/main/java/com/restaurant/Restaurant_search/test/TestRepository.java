package com.restaurant.Restaurant_search.test;

import com.restaurant.Restaurant_search.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<User, Integer> {
}
