package com.soo0.bulletin_board.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BoardInfo implements Board {
    private Integer boardId;
    private Integer parentId;
    private String boardName;

    public BoardInfo(String boardName) {
        this.boardName = boardName;
    }

    public BoardInfo(String boardName, Integer parentId) {
        this(boardName);
        this.parentId = parentId;
    }
}
