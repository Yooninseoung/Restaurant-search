package com.restaurant.Restaurant_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/ranking-search")
    public String showRankingSearchPage() {
        return "search/Rankingsearch"; // "search/Rankingsearch.html"을 반환
    }

    @GetMapping("/regional-search")
    public String showRegionalSearchPage() {
        return "search/regionalsearch"; // "search/regionalsearch.html"을 반환
    }

    @GetMapping("/freeBoard")
    public String showFreeBoardPage() {
        return "board/freeBoard"; // "freeBoard.html"을 반환
    }
}
