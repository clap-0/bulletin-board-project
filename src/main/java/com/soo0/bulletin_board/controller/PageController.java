package com.soo0.bulletin_board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
