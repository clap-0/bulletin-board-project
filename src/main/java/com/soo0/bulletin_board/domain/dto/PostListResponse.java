package com.soo0.bulletin_board.domain.dto;

import com.soo0.bulletin_board.domain.vo.PostInfo;
import lombok.Getter;

import java.util.List;

/**
 * 게시글 목록과 페이징 정보를 담은 DTO 이다.
 */
@Getter
public class PostListResponse {
    /**
     * 조회할 게시글 목록
     */
    private final List<PostResponse> postList;

    /**
     * 현재 페이지 번호
     */
    private final int currentPage;

    /**
     * 전체 페이지 수
     */
    private final int totalPageCount;

    /**
     * 현재 페이지 기준으로 시작하는 페이지 번호
     */
    private final int beginPage;

    /**
     * 현재 페이지 기준으로 끝나는 페이지 번호
     */
    private final int endPage;

    /**
     * 페이지 네비게이터에 표시될 페이지 번호 개수
     */
    private final int navLimit = 10;

    /**
     * PostListResponse 클래스의 생성자이다.
     *
     * @param postList 조회할 게시글 목록을 저장하는 List<PostInfo> 객체
     * @param currentPage 페이지 네비게이터에 표시될 현재 페이지 번호
     * @param totalPageCount 총 페이지 개수
     */
    public PostListResponse(List<PostResponse> postList, int currentPage, int totalPageCount) {
        this.postList = postList;
        this.currentPage = currentPage;
        this.totalPageCount = totalPageCount;

        // 현재 페이지 번호를 기준으로 페이지 네비게이터에 표시될 시작 번호와 끝 번호 계산
        this.beginPage = (currentPage - 1) / navLimit * navLimit + 1;
        this.endPage = Math.min(totalPageCount, this.beginPage + navLimit - 1);
    }
}
