package com.soo0.bulletin_board.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 목록 조회에 대한 요청 정보를 담은 DTO 이다.
 */
@Getter @Setter
public class PostListRequest {
    /**
     * 조회할 페이지 번호
     */
    private Integer page;

    /**
     * 페이지당 게시글 개수
     */
    private Integer pageSize = 10;

    /**
     * 조회할 게시판 ID
     */
    private Integer boardId;

    /**
     * 현재 페이지 번호와 페이지당 게시글 개수를 바탕으로 조회 시작 위치를 계산하여 반환하는 메서드
     *
     * @return 조회 시작 위치(offset)
     */
    public int getOffset() {
        return (page - 1) * pageSize;
    }
}
