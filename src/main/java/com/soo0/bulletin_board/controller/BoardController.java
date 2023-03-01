package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.vo.BoardInfo;
import com.soo0.bulletin_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 게시판 기능을 담당하는 컨트롤러 클래스이다.
 */
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /**
     * 게시판 목록을 조회하는 메서드이다.
     *
     * @return 조회할 게시판 목록을 담은 List<BoardInfo> 객체와 HTTP 상태 코드를 포함하는 ResponseEntity 객체
     */
    @GetMapping
    public ResponseEntity<List<BoardInfo>> list() {
        List<BoardInfo> list;
        try {
            list = boardService.list();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 전체 하위 게시판 목록을 조회하는 메서드이다.
     *
     * @return 조회할 게시판 목록을 담은 List<BoardInfo> 객체와 HTTP 상태 코드를 포함하는 ResponseEntity 객체
     */
    @GetMapping("/sub")
    public ResponseEntity<List<BoardInfo>> listSub() {
        List<BoardInfo> list;
        try {
            list = boardService.listSub();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 새로운 게시판을 추가하는 메서드이다.
     *
     * @param boardInfo 추가할 게시판의 정보를 담은 BoardInfo 객체
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BoardInfo boardInfo, HttpSession session) {
        // 현재 로그인한 사용자의 ID를 세션에서 가져옴
        // 사용자가 관리자 권한을 가지고 있는지 BoardService 에서 확인하기 위해 사용됨
        Integer userId = (Integer) session.getAttribute("id");

        boardService.create(boardInfo, userId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 게시판 정보를 수정하는 메서드이다.
     *
     * @param boardId 수정할 게시판의 ID
     * @param boardInfo 수정할 내용을 담은 BoardInfo 객체
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     */
    @PatchMapping("/{boardId}")
    public ResponseEntity<Void> modify(@PathVariable Integer boardId, @RequestBody BoardInfo boardInfo,
                                            HttpSession session) {
        // 현재 로그인한 사용자의 ID를 세션에서 가져옴
        Integer userId = (Integer) session.getAttribute("id");
        boardInfo.setBoardId(boardId);

        boardService.modify(boardInfo, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 게시판을 삭제하는 메서드이다.
     *
     * @param boardId 삭제할 게시판의 ID
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> remove(@PathVariable Integer boardId, HttpSession session) {
        // 현재 로그인한 사용자의 ID를 세션에서 가져옴
        Integer userId = (Integer) session.getAttribute("id");

        boardService.remove(boardId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
