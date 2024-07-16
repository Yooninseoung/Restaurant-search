package com.restaurant.Restaurant_search.test;

import com.restaurant.Restaurant_search.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DbTest {
    @Autowired
    private TestRepository testRepository;

    @GetMapping("/insert")
    public String insert() {
        User user = new User( "2", "3", "4", "5", "6", "7");
        testRepository.save(user);
        return "index";
    }

}
