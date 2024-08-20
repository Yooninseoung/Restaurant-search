package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.BoardLike;
import com.restaurant.Restaurant_search.repository.BoardLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardLikeService {
    @Autowired
    BoardLikeRepository boardlikeRepository;

    public void addBoardLike(BoardLike boardlike){
        boardlikeRepository.save(boardlike);

    }

    public void deleteBoardLike(BoardLike boardLike){
        boardlikeRepository.delete(boardLike);

    }

    public boolean existBoardLike(BoardLike boardLike){ // 북마크가 존재한다면 true
        return boardlikeRepository.existsByBoardIdAndUserId
                (boardLike.getCommonBoardLikeID().getBoardId(), boardLike.getCommonBoardLikeID().getUserID());
    }

    public int countBoardLike(Integer boardId){
        return boardlikeRepository.countByBoardId(boardId);
    }
}
