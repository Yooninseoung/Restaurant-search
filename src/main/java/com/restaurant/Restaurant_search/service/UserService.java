package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void insert(User user) {

        userRepository.save(user);

    }

    public boolean authenticate(String userId, String password) { //회원 인증
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }

    public List<User> searchUser(String searchId) { // 회원 아이디로 정보 찾기
        List<User> user = userRepository.searchByUserId(searchId);
        return user;
    }

    public void deleteUserById(String userId){ //회원 삭제
        userRepository.deleteById(userId);
        System.out.print(userId);
    }

    public User findByUserId(String userId){

        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null); // 유져 존재하지 않으면 null을 반환하거나 예외를 던질 수 있음

    }
}
