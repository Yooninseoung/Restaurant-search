package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Comment;
import com.restaurant.Restaurant_search.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@RequestParam Integer boardId,
                             @RequestParam String content,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        // 세션에서 사용자 이름을 가져오기
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리디렉션
            return "redirect:/login";
        }

        // 댓글 추가
        Comment comment = commentService.addComment(boardId, username, content);

        // 리디렉션 시 사용할 게시판 ID를 모델에 추가
        redirectAttributes.addAttribute("boardId", boardId);

        // 게시판 상세 페이지로 리디렉션
        return "redirect:/board/detail/" + boardId;
    }
}
