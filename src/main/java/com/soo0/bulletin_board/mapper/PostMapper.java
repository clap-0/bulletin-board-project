package com.soo0.bulletin_board.mapper;

import com.soo0.bulletin_board.domain.dto.PostListRequest;
import com.soo0.bulletin_board.domain.dto.PostSaveRequest;
import com.soo0.bulletin_board.domain.vo.Post;
import com.soo0.bulletin_board.domain.vo.PostInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 게시글 기능을 담당하는 Mapper 클래스이다.
 */
@Mapper
public interface PostMapper {
    /**
     * 게시글을 추가하는 메서드이다.
     *
     * @param request 추가할 게시글 정보를 담은 PostSaveRequest 객체
     * @return 추가된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int insert(PostSaveRequest request);

    /**
     * 게시글 정보를 조회하는 메서드이다.
     *
     * @param postId 조회할 게시글의 ID
     * @return 조회할 게시글 정보를 담은 PostInfo 객체
     */
    PostInfo select(Integer postId);

    /**
     * 특정 게시판의 게시글 목록을 조회하는 메서드이다.
     * 게시판 ID가 null인 경우, 모든 게시글 목록을 조회한다.
     *
     * @param request 게시글 목록 조회 요청을 담은 PostListRequest 객체
     * @return 조회할 게시글 목록을 담은 List<PostInfo> 객체
     */
    List<PostInfo> selectAll(PostListRequest request);

    /*
     * TODO - 특정 사용자가 작성한 게시글 목록을 조회하는 메서드이다.
     */

    /**
     * 데이터베이스에 저장된 게시글 수를 반환하는 메서드이다.
     *
     * @return 총 게시글 수
     */
    int count();

    /**
     * 게시글 정보를 수정하는 메서드이다.
     *
     * @param request 수정할 게시글 정보를 담은 PostSaveRequest 객체
     * @param userId 수정 요청을 보낸 사용자 ID
     * @return 수정된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int update(@Param("request") PostSaveRequest request, @Param("userId") Integer userId);

    /**
     * 게시글의 조회 수를 증가시키는 메서드이다.
     *
     * @param postId 수정하려는 게시글 ID
     * @return 수정된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int updateViewCnt(Integer postId);

    /**
     * 게시글의 댓글 수를 증가 혹은 감소시키는 메서드이다.
     *
     * @param postId 수정하려는 게시글 ID
     * @param cnt 댓글 수 증감분
     * @return 수정된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int updateCommentCnt(@Param("postId") Integer postId, @Param("cnt") Integer cnt);

    /**
     * 게시글의 좋아요 수를 증가 혹은 감소시키는 메서드이다.
     *
     * @param postId 수정하려는 게시글 ID
     * @param cnt 좋아요 수 증감분
     * @return 수정된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int updateLikeCnt(@Param("postId") Integer postId, @Param("cnt") Integer cnt);

    /**
     * 게시글을 삭제하는 메서드이다.
     *
     * @param postId 삭제하려는 게시글 ID
     * @param userId 삭제 요청을 보낸 사용자 ID
     * @return 삭제된 데이터베이스 행의 개수. 1: 성공, 0: 실패, -1: 오류 발생
     */
    int delete(@Param("postId") Integer postId, @Param("userId") Integer userId);
}
