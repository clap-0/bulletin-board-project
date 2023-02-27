package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.dto.PostListRequest;
import com.soo0.bulletin_board.domain.dto.PostListResponse;
import com.soo0.bulletin_board.domain.dto.PostSaveRequest;
import com.soo0.bulletin_board.domain.vo.Post;
import com.soo0.bulletin_board.domain.vo.PostInfo;
import com.soo0.bulletin_board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 게시글 기능을 담당하는 컨트롤러 클래스이다.
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * 게시글 목록을 조회하는 메서드이다.
     *
     * @param request 게시글 목록 조회 요청 정보를 담은 PostListRequest 객체
     * @return 게시글 목록과 페이징 정보를 담은 PostListResponse 객체
     */
    @GetMapping
    public ResponseEntity<PostListResponse> list(@RequestBody PostListRequest request) {
        PostListResponse response = postService.list(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 게시글을 읽어오는 메서드이다.
     *
     * @param postId 읽어올 게시글 ID
     * @return 게시글 정보를 담은 Post 객체
     */
    @GetMapping("/{postId}")
    public ResponseEntity<Post> read(@PathVariable Integer postId) {
        Post post = postService.read(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * 게시글을 등록하는 메서드이다.
     *
     * @param request 등록할 게시글 정보를 담은 PostSaveRequest 객체
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostSaveRequest request, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        request.setUserId(userId);

        postService.create(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 게시글을 수정하는 메서드이다.
     *
     * @param postId 수정할 게시글 ID
     * @param request 수정할 게시글 정보를 담은 PostSaveRequest 객체
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     */
    @PatchMapping("/{postId}")
    public ResponseEntity<Void> modify(@PathVariable Integer postId, @RequestBody PostSaveRequest request,
                                        HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        request.setPostId(postId);

        postService.modify(request, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 게시글을 삭제하는 메서드이다.
     *
     * @param postId 삭제할 게시글 ID
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> remove(@PathVariable Integer postId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");

        postService.remove(postId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
