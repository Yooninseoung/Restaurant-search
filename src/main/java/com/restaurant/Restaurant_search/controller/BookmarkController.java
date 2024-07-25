package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.service.BookmarkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;

    @RequestMapping("/mark") //즐겨찾기 추가 버튼
    public String bookmark(HttpServletRequest req){

        //HttpSession session = req.getSession();
        //String userId = String.valueOf(session.getAttribute("userId"));
        String userId = "admin"; // 세션 기능 미완성, 대체제

        Integer restaurantID = Integer.parseInt(req.getParameter("restaurantId"));

        bookmarkService.addBookmark(restaurantID, userId);

        return "redirect:/restaurant/detailScreen?restaurantId=" + restaurantID;



    }
}
