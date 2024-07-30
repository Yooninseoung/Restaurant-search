package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void insert(User user) {

        userRepository.save(user);

    }

    public boolean authenticate(String userId, String password) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
