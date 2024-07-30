package com.restaurant.Restaurant_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingController {
    @GetMapping("/ranking")
    public String showRankingSearchPage() {
        return "search/Rankingsearch"; // "search/Rankingsearch.html"을 반환
    }
}
