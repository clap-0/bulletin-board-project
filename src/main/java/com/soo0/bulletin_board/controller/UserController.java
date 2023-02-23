package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.vo.User;
import com.soo0.bulletin_board.domain.vo.UserInfo;
import com.soo0.bulletin_board.domain.dto.SignupRequest;
import com.soo0.bulletin_board.service.UserService;
import com.soo0.bulletin_board.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MessageSource messageResource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new SignupRequestValidator());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationResult handleUserFormBindException(BindException bindException, Locale locale) {
        return ValidationResult.create(bindException, messageResource, locale);
    }

    // 회원가입 페이지로 이동
    @GetMapping("/users/new")
    public String signup() {
        return "views/signup";
    }

    // 회원가입 페이지에서 회원 등록
    @PostMapping("/users/new")
    @ResponseBody
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) throws BindException {
        if (userService.validateDuplicateUser(signupRequest.getEmail())) {
            bindingResult.rejectValue("email", "duplicate");
        }
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        User user = new UserInfo(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getUserName());
        userService.signup(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}