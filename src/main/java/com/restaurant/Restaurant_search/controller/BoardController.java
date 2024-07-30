package com.restaurant.Restaurant_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/board")
    public String showFreeBoardPage() {
        return "board/freeBoard"; // "freeBoard.html"을 반환
    }
}
