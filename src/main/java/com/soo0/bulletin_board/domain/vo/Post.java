package com.soo0.bulletin_board.domain.vo;

import java.util.Date;

/**
 * 게시글 정보를 나타내는 인터페이스이다.
 */
public interface Post {
    /**
     * 게시글 ID를 반환하는 메서드이다.
     * @return 게시글 ID
     */
    Integer getPostId();

    /**
     * 게시글이 속한 게시판 ID를 반환하는 메서드이다.
     * @return 게시글이 속한 게시판 ID
     */
    Integer getBoardId();

    /**
     * 게시글 작성자의 ID를 반환하는 메서드이다.
     * @return 게시글 작성자 ID
     */
    Integer getUserId();

    /**
     * 게시글의 제목을 반환하는 메서드이다.
     * @return 게시글 제목
     */
    String getTitle();

    /**
     * 게시글의 내용을 반환하는 메서드이다.
     * @return 게시글 내용
     */
    String getContent();

    /**
     * 게시글 조회 수를 반환하는 메서드이다.
     * @return 게시글 조회 수
     */
    Integer getViewCnt();

    /**
     * 게시글 댓글 수를 반환하는 메서드이다.
     * @return 게시글 댓글 수
     */
    Integer getCommentCnt();

    /**
     * 게시글 좋아요 수를 반환하는 메서드이다.
     * @return 게시글 좋아요 수
     */
    Integer getLikeCnt();

    /**
     * 게시글 작성일을 반환하는 메서드이다.
     * @return 게시글 작성일
     */
    Date getCreatedDate();

    /**
     * 게시글 수정일을 반환하는 메서드이다.
     * @return 게시글 수정일
     */
    Date getModifiedDate();
}
