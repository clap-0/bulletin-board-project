package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.PostListRequest;
import com.soo0.bulletin_board.domain.dto.PostListResponse;
import com.soo0.bulletin_board.domain.dto.PostSaveRequest;
import com.soo0.bulletin_board.domain.vo.Post;
import com.soo0.bulletin_board.domain.vo.PostInfo;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.exception.DataModificationException;
import com.soo0.bulletin_board.exception.ErrorCode;
import com.soo0.bulletin_board.exception.PostNotFoundException;
import com.soo0.bulletin_board.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 게시글과 관련된 비즈니스 로직을 처리하는 Service 계층의 클래스이다.
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    /**
     * 데이터베이스 행 추가/수정/삭제 실패
     */
    private static final int FAIL = 0;
    private final PostMapper postMapper;

    /**
     * 게시글 목록을 조회하는 메서드이다.
     *
     * @param request 게시글 목록 조회 요청을 담은 PostListRequest 객체
     * @return 게시글 목록과 페이징 정보를 담은 PostListResponse 객체
     */
    @Override
    public PostListResponse list(PostListRequest request) {
        int totalPageCount = getTotalPageCount(request.getPageSize());
        if (totalPageCount < request.getPage()) {
            request.setPage(totalPageCount);
        }

        List<PostInfo> postList = postMapper.selectAll(request);


        return new PostListResponse(postList, request.getPage(), totalPageCount);
    }

    /**
     * 게시글을 조회하는 메서드이다.
     *
     * @param postId 조회할 게시글 ID
     * @return 조회된 게시글 정보
     */
    @Override
    @Transactional
    public PostInfo read(Integer postId) {
        PostInfo postInfo = getPostInfo(postId);

        int result = postMapper.updateViewCnt(postId);
        checkResult(result, "Failed to read post");

        return postInfo;
    }

    /**
     * 게시글을 등록하는 메서드이다.
     *
     * @param request 등록할 게시글 정보를 담은 PostSaveRequest 객체
     */
    @Override
    @Transactional
    public void create(PostSaveRequest request) {
        checkLogin(request.getUserId());

        int result = postMapper.insert(request);
        checkResult(result, "Failed to create post");
    }

    /**
     * 게시글 정보를 수정하는 메서드이다.
     *
     * @param request 수정할 게시글 정보를 담은 PostSaveRequest 객체
     * @param requestorId 수정 요청을 보낸 사용자 ID
     *
     */
    @Override
    @Transactional
    public void modify(PostSaveRequest request, Integer requestorId) {
        PostInfo postInfo = getPostInfo(request.getPostId());

        checkPermission(requestorId, postInfo.getUserId());

        int result = postMapper.update(request, requestorId);
        checkResult(result, "Failed to modify post");
    }

    /**
     * 게시글을 삭제하는 메서드이다.
     *
     * @param postId 삭제하려는 게시글 ID
     * @param requestorId 삭제 요청을 보낸 사용자 ID
     */
    @Override
    @Transactional
    public void remove(Integer postId, Integer requestorId) {
        PostInfo postInfo = getPostInfo(postId);

        checkPermission(requestorId, postInfo.getUserId());

        int result = postMapper.delete(postId, requestorId);
        checkResult(result, "Failed to remove post");
    }

    /**
     * 총 게시글 수에 따른 전체 페이지 수를 계산하는 메서드
     *
     * @param pageSize 페이지당 게시글 개수
     * @return 전체 페이지 수
     */
    private int getTotalPageCount(Integer pageSize) {
        return (int) Math.ceil((double) postMapper.count() / pageSize);
    }

    /**
     * 게시글 정보를 반환하는 메서드이다.
     *
     * @param postId 반환할 게시글 ID
     * @return 게시글 정보를 담은 PostInfo 객체
     * @throws PostNotFoundException 게시글이 존재하지 않는 경우 발생하는 예외
     */
    private PostInfo getPostInfo(Integer postId) throws PostNotFoundException {
        PostInfo postInfo = null;

        if (postId != null) {
            postInfo = postMapper.select(postId);
        }

        if (postInfo == null) {
            throw new PostNotFoundException(ErrorCode.POST_NOT_FOUND, "Post not found");
        }

        return postInfo;
    }

    /**
     * 데이터의 생성/수정/삭제가 이루어졌는지 확인하는 메서드이다.
     *
     * @param result 데이터베이스에서 변경된 행의 수
     * @param message 에러 발생시 출력할 메시지
     * @throws DataModificationException 데이터베이스 행이 변경되지 않았을 때 발생하는 예외
     */
    private void checkResult(int result, String message) throws DataModificationException {
        if (result == FAIL) {
            throw new DataModificationException(ErrorCode.DATA_MODIFICATION_ERROR, message);
        }
    }

    /**
     * 로그인 중이 아닌 사용자가 접근했는지 확인하는 메서드이다.
     *
     * @param userId 사용자 ID
     * @throws InsufficientAuthenticationException 로그인 하지 않은 사용자인 경우 발생하는 예외
     */
    private void checkLogin(Integer userId) throws InsufficientAuthenticationException {
        if (userId == null) {
            throw new InsufficientAuthenticationException("Access denied: authentication required");
        }
    }

    /**
     * 게시글 수정/삭제 요청을 보낸 사용자가 해당 게시물의 작성자인지 확인하는 메서드이다.
     *
     * @param requestorId 요청자 ID
     * @param writerId 작성자 ID
     * @throws AccessDeniedException 다른 사용자의 게시글을 수정/삭제하려고 할 때 발생하는 예외
     */
    private void checkPermission(Integer requestorId, Integer writerId) throws AccessDeniedException {
        checkLogin(requestorId);

        if (!requestorId.equals(writerId)) {   // TODO - 스프링 시큐리티 배우면 수정할 것
            throw new AccessDeniedException("Permission denied");
        }
    }
}
