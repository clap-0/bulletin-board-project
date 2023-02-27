package com.soo0.bulletin_board.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 게시글 정보를 나타내는 클래스이다.
 */
@Getter @Setter
@NoArgsConstructor
public class PostInfo implements Post {
    /**
     * 게시글 ID. 게시글 생성시 자동으로 등록됨
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

    /**
     * 게시글 조회 수
     */
    private Integer viewCnt;

    /**
     * 게시글 댓글 수
     */
    private Integer commentCnt;

    /**
     * 게시글 좋아요 수
     */
    private Integer likeCnt;

    /**
     * 게시글 생성일
     */
    private Date createdDate;

    /**
     * 게시글 수정일
     */
    private Date modifiedDate;
}
