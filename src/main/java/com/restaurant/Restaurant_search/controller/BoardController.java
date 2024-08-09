package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 페이지를 보여주는 메소드
    @GetMapping
    public String showFreeBoardPage(Model model) {
        List<Board> boards = boardService.getAllBoards(); // 모든 게시글을 가져옴
        model.addAttribute("boards", boards); // 모델에 게시글 목록 추가
        return "board/freeBoard"; // "board/freeBoard.html"을 반환
    }

    // 게시글 작성 페이지를 보여주는 메소드
    @GetMapping("write")
    public String showFreeBoardWritePage() {
        return "board/freeBoardWrite"; // "board/freeBoardWrite.html"을 반환
    }

    // 게시글을 저장하는 메소드
    @PostMapping
    public String saveBoard(@ModelAttribute Board board, HttpSession session) {
        // 세션에서 username 가져오기
        String username = (String) session.getAttribute("username");
        board.setUsername(username); // 세션에서 username을 가져와서 설정
        boardService.saveBoard(board); // 게시글을 저장
        return "redirect:/board"; // 게시글 목록 페이지로 리다이렉트
    }
}
