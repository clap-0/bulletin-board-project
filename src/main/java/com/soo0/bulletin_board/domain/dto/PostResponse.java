package com.soo0.bulletin_board.domain.dto;

import com.soo0.bulletin_board.domain.vo.Post;
import com.soo0.bulletin_board.domain.vo.PostInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 게시글 정보를 뷰에 보내는 DTO 이다.
 */
@Getter @Setter
public class PostResponse extends PostInfo {
    /**
     * 게시글 작성자 이름
     */
    private String userName;

    /**
     * 게시판 이름
     */
    private String boardName;
}
