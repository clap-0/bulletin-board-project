package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.vo.Board;
import com.soo0.bulletin_board.domain.vo.BoardInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 게시판과 관련된 비즈니스 로직을 처리하는 Service 계층의 인터페이스이다.
 */
public interface BoardService {
    /**
     * 게시판 목록을 조회하는 메서드이다.
     *
     * @return 조회할 게시판 목록을 담은 List<BoardInfo> 객체
     */
    List<BoardInfo> list();

    /**
     * 사용자가 관리자인지 확인 후 게시판을 추가하는 메서드이다.
     *
     * @param board 추가할 게시판의 정보를 담은 Board 객체
     * @param userId 사용자 ID
     */
    @Transactional
    void create(Board board, Integer userId);

    /**
     * 사용자가 관리자인지 확인 후 게시판 정보를 수정하는 메서드이다.
     *
     * @param board 수정할 내용을 담은 Board 객체
     * @param userId 사용자 ID
     */
    @Transactional
    void modify(Board board, Integer userId);

    /**
     * 사용자가 관리자인지 확인 후 게시판을 삭제하는 메서드이다.
     *
     * @param boardId 삭제할 게시판의 ID
     * @param userId 사용자 ID
     */
    @Transactional
    void remove(Integer boardId, Integer userId);
}
