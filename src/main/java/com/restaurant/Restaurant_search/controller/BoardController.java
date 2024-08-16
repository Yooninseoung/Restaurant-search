package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 페이지를 보여주는 메소드 (페이징 기능 추가)
    @GetMapping
    public String showFreeBoardPage(@PageableDefault(page = 0, size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        Page<Board> boardsPage = boardService.getAllBoards(pageable); // 서비스 호출
        int totalPages = boardsPage.getTotalPages();
        int currentPage = boardsPage.getNumber() + 1; // 현재 페이지 (0부터 시작)

        // 시작 페이지 계산: 현재 페이지를 기준으로 앞 4개 페이지를 보여줌
        int startPage = Math.max(1, currentPage - 4);

        // 종료 페이지 계산: 현재 페이지를 기준으로 뒤 5개 페이지를 보여줌
        int endPage = Math.min(totalPages, currentPage + 5);

        model.addAttribute("boards", boardsPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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

    @GetMapping("detail")
    public String showFreeBoardDetailPage() {
        return "board/freeBoardDetail"; // "board/freeBoardDetail.html"을 반환
    }

    // 게시글 디테일 페이지를 보여주는 메소드
    @GetMapping("/detail/{boardId}")
    public String showBoardDetailPage(@PathVariable("boardId") Integer boardId, Model model) {
        Optional<Board> optionalBoard = boardService.getBoardById(boardId);

        //명확한 NULL 처리를 위해 사용
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get(); // Optional에서 실제 Board 객체를 추출
            model.addAttribute("board", board);
            return "board/freeBoardDetail"; // "board/boardDetail.html"을 반환
        } else {
            return "redirect:/board"; // 게시글 목록 페이지로 리다이렉트
        }
    }
}