package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 페이징을 포함한 게시글 목록을 반환
    public Page<Board> getAllBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    // 게시글 ID로 게시글 찾기
    public Optional<Board> getBoardById(Integer boardId) {
        return boardRepository.findById(boardId);
    }

    // 게시글 저장
    public void saveBoard(Board board) {
        // 제목이 비어 있거나 null일 경우 예외를 던짐
        if (!StringUtils.hasText(board.getTitle())) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        boardRepository.save(board);
    }

    public void deleteBoard(Integer boardId) {
        boardRepository.deleteById(boardId);
    }


}
