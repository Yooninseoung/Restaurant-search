package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Bookmark;
import com.restaurant.Restaurant_search.entity.CommonDataID;
import com.restaurant.Restaurant_search.entity.LikeData;
import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.service.BookmarkService;
import com.restaurant.Restaurant_search.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.awt.print.Book;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//식당 북마크 기능
@Controller
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/mark/{where}", method = RequestMethod.GET) //즐겨찾기 추가 버튼
    public String bookmark(HttpServletRequest req,
                           @PathVariable("where") String where,
                           @SessionAttribute(name = "userId", required = false) String userId) throws UnsupportedEncodingException {

        Integer restaurantID = Integer.parseInt(req.getParameter("restaurantId"));


        CommonDataID commonDataID = new CommonDataID(userId, restaurantID); //북마크 table의 기본키
        boolean flag;

        flag = bookmarkService.existBookmrk(restaurantID, userId); //북마크 테이블에 데이터가 존재하는지 확인
        Bookmark bookmark = new Bookmark(commonDataID); // entity객체
        if (flag) { //true : 데이터가 있음 -> 데이터를 삭제
            bookmarkService.deleteBookmark(bookmark);
        } else {
            bookmarkService.addBookmark(bookmark);
        }

        int bookmarkCount = bookmarkService.countBookmark(restaurantID); //특정 식당의 북마크 수를 갖고옴.
        // 변경된 북마크 수를 식당에 저장함(정렬을 위해서)
        Restaurant restaurant = restaurantService.findRestaurant(restaurantID);
        restaurant.setBookmarks(bookmarkCount);
        restaurantService.addRestaurant(restaurant);


        if (where.equals("index")) { //즐겨찾기 버튼을 누를 수 있는 화면 3곳 : 각 요청한 곳으로 반환
            return "redirect:/index";
        } else if (where.equals("detail")) {
            return "redirect:/restaurant/detailScreen?restaurantId=" + restaurantID;
        } else if (where.equals("ranking")) {
            return "redirect:/ranking";
        } else if (where.equals("search")) {
            return "redirect:/index";
        } else if (where.equals("favorites")) {
            return "redirect:/restaurant/favorites";
        } else {
            return "redirect:/restaurant/restaurantAllPage";
        }

    }

}
