package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Integer boardId) {
        return boardRepository.findById(boardId);
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public void deleteBoard(Integer boardId) {
        boardRepository.deleteById(boardId);
    }
}
