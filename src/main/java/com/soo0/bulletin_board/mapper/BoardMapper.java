package com.soo0.bulletin_board.mapper;

import com.soo0.bulletin_board.domain.vo.Board;
import com.soo0.bulletin_board.domain.vo.BoardInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 게시판 기능을 담당하는 Mapper 인터페이스이다.
 */
@Mapper
public interface BoardMapper {
    /**
     * 게시판을 추가하는 메서드이다.
     *
     * @param board 추가할 게시판의 정보를 담은 Board 객체
     * @return 추가된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int insert(Board board);

    /**
     * 모든 게시판 목록을 추가된 순서로 조회하는 메서드이다.
     *
     * @return 조회할 게시판 목록을 담은 List<BoardInfo> 객체
     */
    List<BoardInfo> selectAll();

    /**
     * 전체 하위 게시판 목록을 조회하는 메서드이다.
     *
     * @return 조회할 하위 게시판 목록을 담은 List<BoardInfo> 객체
     */
    List<BoardInfo> selectChildAll();

    /**
     * 게시판의 개수를 반환하는 메서드이다.
     *
     * @return 데이터베이스에 저장된 게시판의 수
     */
    int count();

    /**
     * 게시판의 정보를 수정하는 메서드이다.
     *
     * @param board 수정할 게시판 정보를 담은 Board 객체
     * @return 수정된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int update(Board board);

    /**
     * 게시판을 삭제하는 메서드이다.
     *
     * @param boardId 삭제할 게시판의 ID
     * @return 삭제된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int delete(Integer boardId);
}
