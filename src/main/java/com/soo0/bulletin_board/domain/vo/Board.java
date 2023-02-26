package com.soo0.bulletin_board.domain.vo;

/**
 * 게시판 정보를 나타내는 인터페이스이다.
 */
public interface Board {
    /**
     * 게시판의 ID를 반환하는 메서드이다.
     * @return 게시판 ID
     */
    Integer getBoardId();

    /**
     * 상위 게시판의 ID를 반환하는 메서드이다.
     * @return 상위 게시판 ID
     */
    Integer getParentId();

    /**
     * 게시판의 이름을 반환하는 메서드이다.
     * @return 게시판 이름
     */
    String getBoardName();
}
