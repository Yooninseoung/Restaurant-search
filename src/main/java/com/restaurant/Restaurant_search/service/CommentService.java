package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.entity.Comment;
import com.restaurant.Restaurant_search.repository.BoardRepository;
import com.restaurant.Restaurant_search.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    public Comment addComment(Integer boardId, String username, String content) {
        System.out.println("Adding comment: boardId=" + boardId + ", username=" + username + ", content=" + content);

        // 게시글이 존재하는지 확인
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        // 댓글 생성
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setContent(content);
        comment.setBoard(board);
        comment.setCreatedDate(LocalDateTime.now());  // 생성 일시 설정
        comment.setUpdatedDate(LocalDateTime.now());  // 수정 일시 설정

        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);
        System.out.println("Saved comment: " + savedComment);

        return savedComment;
    }

    // 댓글 조회 메소드
    public List<Comment> getCommentsByBoardId(Integer boardId) {
        // Board 엔티티를 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        // Board 객체를 사용하여 댓글 조회
        return commentRepository.findByBoard(board);
    }

    public void deleteComment(Integer commentId){
        commentRepository.deleteById(commentId);
    }
}