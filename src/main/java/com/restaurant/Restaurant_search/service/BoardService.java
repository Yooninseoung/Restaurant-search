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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    // 게시글 저장 및 파일 업로드 메소드
    public void writeBoard(Board board, MultipartFile file, String username) throws IOException {
        Integer boardId = board.getBoardId(); // 게시글 ID 가져오기
        String photoPath = uploadFile(file, boardId); // 파일 업로드 경로

        board.setPhotoPath(photoPath); // 게시글에 파일 경로 설정
        board.setUsername(username); // 작성자 설정

        boardRepository.save(board); // 게시글 저장
    }

    // 게시글 저장
    public void saveBoard(Board board) {
        // 제목이 비어 있거나 null일 경우 예외를 던짐
        if (!StringUtils.hasText(board.getTitle())) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        boardRepository.save(board);
    }

    // 파일을 업로드하는 메소드
    public String uploadFile(MultipartFile file, Integer boardId) throws IOException {
        String projectPath = "src/main/resources/static/img/boardImg"; // 파일 저장 경로
        File directory = new File(projectPath);

        // 디렉토리가 존재하지 않으면 생성
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 안전한 파일 이름 생성
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "board_" + boardId + "_" + originalFileName;

        File saveFile = new File(directory, fileName);

        // 기존 파일이 있으면 삭제
        if (saveFile.exists()) {
            saveFile.delete();
        }

        // 파일 저장
        Files.copy(file.getInputStream(), saveFile.toPath());

        return "/img/boardImg/" + fileName; // 저장된 파일의 상대 경로 반환
    }


    public void deleteBoard(Integer boardId) {
        boardRepository.deleteById(boardId);
    }


}
