package com.soo0.bulletin_board.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시판 정보를 나타내는 클래스이다.
 */
@Getter @Setter
@NoArgsConstructor
public class BoardInfo implements Board {
    /**
     * 게시판 ID, 게시판 생성시 자동으로 할당됨
     */
    private Integer boardId;

    /**
     * 상위 게시판 ID
     */
    private Integer parentId;

    /**
     * 게시판 이름
     */
    private String boardName;

    /**
     * BoardInfo의 생성자이다.
     *
     * @param boardName 게시판 이름
     */
    public BoardInfo(String boardName) {
        this.boardName = boardName;
    }

    /**
     * BoardInfo의 생성자이다.
     *
     * @param boardName 게시판 이름
     * @param parentId 상위 게시판 ID
     */
    public BoardInfo(String boardName, Integer parentId) {
        this(boardName);
        this.parentId = parentId;
    }
}
