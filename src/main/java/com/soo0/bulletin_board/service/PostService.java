package com.soo0.bulletin_board.service;

import com.soo0.bulletin_board.domain.dto.PostListRequest;
import com.soo0.bulletin_board.domain.dto.PostListResponse;
import com.soo0.bulletin_board.domain.dto.PostSaveRequest;
import com.soo0.bulletin_board.domain.vo.Post;
import com.soo0.bulletin_board.domain.vo.PostInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글과 관련된 비즈니스 로직을 처리하는 Service 계층의 인터페이스이다.
 */
public interface PostService {
    /**
     * 게시글 목록을 조회하는 메서드이다.
     *
     * @param request 게시글 목록 조회 요청을 담은 PostListRequest 객체
     * @return 게시글 목록과 페이징 정보를 담은 PostListResponse 객체
     */
    PostListResponse list(PostListRequest request);

    /**
     * 게시글을 조회하는 메서드이다.
     *
     * @param postId 조회할 게시글 ID
     * @return 조회된 게시글 정보
     */
    @Transactional
    PostInfo read(Integer postId);

    /**
     * 게시글을 등록하는 메서드이다.
     *
     * @param request 등록할 게시글 정보를 담은 PostSaveRequest 객체
     */
    @Transactional
    void create(PostSaveRequest request);

    /**
     * 게시글 정보를 수정하는 메서드이다.
     *
     * @param request 수정할 게시글 정보를 담은 PostSaveRequest 객체
     * @param userId 수정 요청을 보낸 사용자 ID
     *
     */
    void modify(PostSaveRequest request, Integer userId);

    /**
     * 게시글을 삭제하는 메서드이다.
     *
     * @param postId 삭제하려는 게시글 ID
     * @param userId 삭제 요청을 보낸 사용자 ID
     */
    void remove(Integer postId, Integer userId);
}
