package com.soo0.bulletin_board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    /**
     * 홈 페이지를 보여주는 메서드이다.
     *
     * @return 홈 페이지의 경로를 반환하는 문자열
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "views/index";
    }

    /**
     * 회원가입 페이지를 보여주는 메서드이다.
     *
     * @return 회원가입 페이지의 경로를 반환하는 문자열
     */
    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("pageTitle", "Signup");
        return "views/signup";
    }

    /**
     * 로그인 페이지를 보여주는 메서드이다.
     *
     * @return 로그인 페이지의 경로를 반환하는 문자열
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "views/login";
    }

    /**
     * 게시글 글쓰기 혹은 수정 페이지를 보여주는 메서드이다.
     *
     * @param pno 게시글 수정 요청인 경우, 수정할 게시글의 ID를 쿼리스트링으로 받는다.
     * @param session HTTP 세션 객체
     * @return 이동할 페이지의 경로를 반환하는 문자열
     */
    @GetMapping("/posts/new")
    public String writePost(Integer pno, Model model, HttpSession session) {
        // 로그인 하지 않은 사용자이면 로그인 페이지로 이동
        Integer userId = (Integer) session.getAttribute("id");
        if (userId == null) {
            return "views/login";
        }

        if (pno != null) {
            model.addAttribute("pno", pno);
        }
        return "views/write";
    }
}
