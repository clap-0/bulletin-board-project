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

/**
 * 게시판과 관련된 비즈니스 로직을 처리하는 Service 계층의 클래스이다.
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    /**
     * 데이터베이스 행 추가/수정/삭제 실패
     */
    private final int FAIL = 0;
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    /**
     * 게시판 목록을 조회하는 메서드이다.
     *
     * @return 조회할 게시판 목록을 담은 List<BoardInfo> 객체
     */
    @Override
    public List<BoardInfo> list() {
        return boardMapper.selectAll();
    }

    /**
     * 사용자가 관리자인지 확인 후 게시판을 추가하는 메서드이다.
     *
     * @param board 추가할 게시판의 정보를 담은 Board 객체
     * @param userId 사용자 ID
     */
    @Override
    @Transactional
    public void create(Board board, Integer userId) {
        checkPermission(userId);

        boardMapper.insert(board);
    }

    /**
     * 사용자가 관리자인지 확인 후 게시판 정보를 수정하는 메서드이다.
     *
     * @param board 수정할 내용을 담은 Board 객체
     * @param userId 사용자 ID
     * @throws BoardNotFoundException 존재하지 않는 게시판을 수정하려고 할 때 발생하는 예외
     */
    @Override
    @Transactional
    public void modify(Board board, Integer userId) {
        checkPermission(userId);

        int result = boardMapper.update(board);

        if (result == FAIL) {
            throw new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND, "Board not found");
        }
    }

    /**
     * 사용자가 관리자인지 확인 후 게시판을 삭제하는 메서드이다.
     *
     * @param boardId 삭제할 게시판의 ID
     * @param userId 사용자 ID
     * @throws BoardNotFoundException 존재하지 않는 게시판을 삭제하려고 할 때 발생하는 예외
     */
    @Override
    @Transactional
    public void remove(Integer boardId, Integer userId) {
        checkPermission(userId);

        int result = boardMapper.delete(boardId);

        if (result == FAIL) {
            throw new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND, "Board not found");
        }
    }

    /**
     * 사용자가 관리자 권한을 가지고 있는지 확인하는 메서드이다.
     *
     * @param userId 사용자 ID
     * @throws AccessDeniedException 관리자가 아닌 경우 발생하는 예외
     */
    private void checkPermission(Integer userId) {
        checkLogin(userId);
        User user = checkUserExistence(userId);

        if (!user.getIsAdmin()) {   // TODO - 스프링 시큐리티 배우면 수정할 것
            throw new AccessDeniedException("Permission denied");
        }
    }

    /**
     * 존재하는 사용자의 ID인지 확인하는 메서드이다.
     *
     * @param userId 존재 여부를 확인할 사용자의 ID
     * @return userID를 ID로 갖는 사용자 정보를 담은 User 객체
     * @throws UserNotFoundException 해당 사용자 ID를 가진 사용자가 존재하지 않는 경우 발생하는 예외
     */
    private User checkUserExistence(Integer userId) {
        User user = userMapper.select(userId);

        if (user == null) {
            throw new UserNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND, "User not found");
        }
        return user;
    }

    /**
     * 로그인 중이 아닌 사용자가 접근했는지 확인하는 메서드이다.
     *
     * @param userId 사용자 ID
     * @throws InsufficientAuthenticationException 로그인 하지 않은 사용자인 경우 발생하는 예외
     */
    private void checkLogin(Integer userId) {
        if (userId == null) {
            throw new InsufficientAuthenticationException("Access denied: authentication required");
        }
    }
}
