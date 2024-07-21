package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void insert(User user) {

        userRepository.save(user);

    }
}
