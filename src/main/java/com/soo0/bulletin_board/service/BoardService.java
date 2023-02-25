package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.vo.Board;
import com.soo0.bulletin_board.domain.vo.BoardInfo;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.exception.BoardNotFoundException;
import com.soo0.bulletin_board.exception.ErrorCode;
import com.soo0.bulletin_board.exception.UserNotFoundException;
import com.soo0.bulletin_board.mapper.BoardMapper;
import com.soo0.bulletin_board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final int ERROR = -1;
    private final int FAIL = 0;
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    // 게시판 목록 조회 기능
    public List<BoardInfo> list() {
        return boardMapper.selectAll();
    }

    // 게시판 추가 기능
    @Transactional
    public void create(Board board, Integer userId) {
        checkPermission(userId);

        boardMapper.insert(board);
    }

    // 게시판 정보 수정 기능
    @Transactional
    public void modify(Board board, Integer userId) {
        checkPermission(userId);

        int result = boardMapper.update(board);

        if (result == FAIL) {
            throw new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND, "Board not found");
        }
    }

    // 게시판 삭제 기능
    @Transactional
    public void remove(Integer boardId, Integer userId) {
        checkPermission(userId);

        int result = boardMapper.delete(boardId);

        if (result == FAIL) {
            throw new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND, "Board not found");
        }
    }

    // 사용자 ID를 입력받아 관리자 권한을 가지고 있는지 확인하는 메서드
    private void checkPermission(Integer userId) {
        if (userId == null) {   // 로그인하지 않은 사용자가 접근한 경우
            throw new InsufficientAuthenticationException("Access denied: authentication required");
        }
        User user = userMapper.select(userId);
        if (user == null) { // 사용자가 존재하지 않는 경우
            throw new UserNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND, "User not found");
        }
        if (!user.getIsAdmin()) {   // 관리자가 아닌 경우, AccessDeniedException 발생. 스프링 시큐리티 배우면 수정할 것
            throw new AccessDeniedException("Permission denied");
        }
    }
}
