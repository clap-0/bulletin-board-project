package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.dto.SignupRequest;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.domain.vo.UserInfo;
import com.soo0.bulletin_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 사용자 관련 HTTP 요청을 처리하는 컨트롤러 클래스이다.
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 요청을 처리하는 메서드이다.
     *
     * @param signupRequest 회원가입 요청 정보를 담은 SignupRequest 객체
     * @param bindingResult 유효성 검사 결과를 담은 BindingRequest 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     * @throws BindException signupRequest 파라미터의 유효성 검사 결과 오류가 있는 경우 발생하는 예외
     */
    @PostMapping("/users/new")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult)
            throws BindException {
        // 회원가입 요청 정보의 유효성 검사 결과에 오류가 있으면 BindException 예외 발생
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        // SignupRequest 객체에서 사용자 정보를 추출하여 User 객체 생성
        User user = new UserInfo(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getUserName());
        userService.signup(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 로그인 요청을 처리하는 메서드이다.
     *
     * @param loginRequest  로그인 요청 정보를 담은 LoginRequest 객체
     * @param bindingResult 유효성 검사 결과를 담은 BindingResult 객체
     * @param session       HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     * @throws BindException loginRequest 파라미터의 유효성 검사 결과 오류가 있는 경우 발생하는 예외
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult,
                                      HttpSession session) throws BindException {
        // 로그인 요청 정보의 유효성 검사 결과 오류가 있는 경우 BindException 예외 발생
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Integer userId = userService.login(loginRequest);

        // 로그인한 사용자의 id를 세션에 저장
        session.setAttribute("id", userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 로그아웃 요청을 처리하는 메서드이다.
     *
     * @param session HTTP 세션 객체
     * @return HTTP 상태코드를 포함하는 ResponseEntity 객체
     * @throws IllegalStateException 이미 로그아웃 상태인 경우 발생하는 예외
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        try {
            session.invalidate();
        } catch (IllegalStateException e) {     // 이미 로그아웃 상태인 경우 BAD REQUEST 반환
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}