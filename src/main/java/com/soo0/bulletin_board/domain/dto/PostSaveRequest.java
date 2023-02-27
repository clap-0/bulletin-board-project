package com.soo0.bulletin_board.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 등록 또는 수정 요청 정보를 담은 DTO 클래스이다.
 */
@Getter @Setter
public class PostSaveRequest {
    /**
     * 수정할 게시글 ID
     */
    private Integer postId;

    /**
     * 게시글이 속한 게시판 ID.
     */
    private Integer boardId;

    /**
     * 게시글 작성자 ID
     */
    private Integer userId;

    /**
     * 게시글 제목
     */
    private String title;

    /**
     * 게시글 내용
     */
    private String content;
}
