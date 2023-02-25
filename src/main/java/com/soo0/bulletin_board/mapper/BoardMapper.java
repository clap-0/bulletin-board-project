package com.soo0.bulletin_board.mapper;

import com.soo0.bulletin_board.domain.vo.Board;
import com.soo0.bulletin_board.domain.vo.BoardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시판 추가
    int insert(Board board);

    // 게시판 목록 조회
    List<BoardInfo> selectAll();

    // 하위 게시판 목록 조회
    List<BoardInfo> selectChildAll(Integer parentId);

    // 게시판 수 세기
    int count();

    // 게시판 정보 수정
    int update(Board board);

    // 게시판 삭제
    int delete(Integer boardId);
}
