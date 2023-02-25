package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.vo.BoardInfo;
import com.soo0.bulletin_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시판 목록 조회
    @GetMapping("/boards")
    public ResponseEntity<List<BoardInfo>> list() {
        List<BoardInfo> list;
        try {
            list = boardService.list();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 게시판 추가
    @PostMapping("/boards")
    public ResponseEntity<Void> create(@RequestBody BoardInfo boardInfo, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");

        boardService.create(boardInfo, userId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시판 정보 수정
    @PatchMapping("/boards/{boardId}")
    public ResponseEntity<Void> modify(@PathVariable Integer boardId, @RequestBody BoardInfo boardInfo,
                                            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        boardInfo.setBoardId(boardId);

        boardService.modify(boardInfo, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시판 삭제
    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> remove(@PathVariable Integer boardId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");

        boardService.remove(boardId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
