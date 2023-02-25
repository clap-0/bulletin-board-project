package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.dto.LoginRequest;
import com.soo0.bulletin_board.domain.dto.SignupRequest;
import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.domain.vo.UserInfo;
import com.soo0.bulletin_board.exception.UserNotFoundException;
import com.soo0.bulletin_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입 페이지로 이동
    @GetMapping("/users/new")
    public String signup() {
        return "views/signup";
    }

    // 회원가입 페이지에서 회원 등록
    @PostMapping("/users/new")
    @ResponseBody
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult)
                throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        User user = new UserInfo(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getUserName());
        userService.signup(user);
        return new ResponseEntity<>("User creation success", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public String login() {
        return "views/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult,
                                   HttpSession session) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Integer userId = userService.login(loginRequest);
        // 로그인 상태인 것을 저장하기 위해 세션에 로그인한 사용자의 id를 저장
        session.setAttribute("id", userId);
        return new ResponseEntity<>("Login success", HttpStatus.OK);
    }
}