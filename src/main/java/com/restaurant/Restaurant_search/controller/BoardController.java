package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.entity.Comment;
import com.restaurant.Restaurant_search.entity.CommonReportID;
import com.restaurant.Restaurant_search.entity.Report;
import com.restaurant.Restaurant_search.service.BoardService;
import com.restaurant.Restaurant_search.service.CommentService;
import com.restaurant.Restaurant_search.service.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;  // CommentService 필드 주입 추가

    @Autowired
    private ReportService reportService;

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
    public String saveBoard(@ModelAttribute Board board,
                            @RequestParam("file") MultipartFile file,
                            HttpSession session) throws IOException {
        // 세션에서 username 가져오기
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }
        board.setUsername(username); // 세션에서 username을 가져와서 설정

        boardService.writeBoard(board, file, username); // 게시글과 파일을 저장
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

            // 댓글 목록 추가
            List<Comment> comments = commentService.getCommentsByBoardId(boardId); // CommentService를 통해 댓글 목록 조회
            model.addAttribute("comments", comments);

            return "board/freeBoardDetail"; // "board/boardDetail.html"을 반환
        } else {
            return "redirect:/board"; // 게시글 목록 페이지로 리다이렉트
        }
    }

    // 게시글 수정 페이지로 이동하는 메소드
    @GetMapping("/edit/{boardId}")
    public String showBoardEditPage(@PathVariable("boardId") Integer boardId, Model model) {
        Optional<Board> optionalBoard = boardService.getBoardById(boardId);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get(); // Optional에서 실제 Board 객체를 추출
            model.addAttribute("board", board);
            return "board/freeBoardEdit"; // "board/freeBoardEdit.html"을 반환 (수정 페이지)
        } else {
            return "redirect:/board"; // 게시글 목록 페이지로 리다이렉트
        }
    }

    // 게시글 수정 내용을 저장하는 메소드
    @PostMapping("/edit/{boardId}")
    public String updateBoard(@PathVariable("boardId") Integer boardId,
                              @ModelAttribute Board boardDetails,
                              @RequestParam("file") MultipartFile file,
                              HttpSession session) throws IOException {
        String username = (String) session.getAttribute("username");

        Optional<Board> optionalBoard = boardService.getBoardById(boardId);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get(); // 기존 게시글 가져오기
            if (board.getUsername().equals(username)) { // 세션의 username과 게시글 작성자가 동일한지 확인
                board.setTitle(boardDetails.getTitle());
                board.setContent(boardDetails.getContent());
                if (!file.isEmpty()) {
                    String photoPath = boardService.uploadFile(file, boardId);
                    board.setPhotoPath(photoPath);
                }
                boardService.saveBoard(board); // 게시글 업데이트
            }
        }

        return "redirect:/board/detail/" + boardId; // 수정 후 해당 게시글 디테일 페이지로 리다이렉트
    }

    // 게시글 삭제 메소드
    @PostMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable("boardId") Integer boardId,
                              HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        Optional<Board> optionalBoard = boardService.getBoardById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            if (board.getUsername().equals(username)) { // 세션의 username과 게시글 작성자가 동일한지 확인
                boardService.deleteBoard(boardId); // 게시글 삭제
            }
        }

        return "redirect:/board"; // 게시글 목록 페이지로 리다이렉트
    }

    @GetMapping("report")
    public String addReport (@RequestParam("boardID") Integer boardId,
                             @RequestParam("userId") String userId)     {
        CommonReportID commonReport = new CommonReportID(userId, boardId);
        Report report = new Report(commonReport);

        Board board = boardService.getReportBoardById(boardId);


    if(reportService.existReport(report)){
        reportService.deleteReport(report);
    }
    else {
        reportService.addReport(report);
    }

        board.setReports(reportService.countReport(boardId));
        boardService.saveBoard(board);

        return "redirect:/board/detail/" + boardId;

    }

}