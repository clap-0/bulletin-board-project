package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.UserDto;
import com.soo0.bulletin_board.domain.UserFormDto;
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
        binder.addValidators(new UserFormValidator());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationResult handleUserFormBindException(BindException bindException, Locale locale) {
        return ValidationResult.create(bindException, messageResource, locale);
    }

    // 회원가입 페이지로 이동
    @GetMapping("/signup")
    public String signup() {
        return "views/signup";
    }

    // 회원가입 페이지에서 회원 등록
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@Valid @RequestBody UserFormDto formDto, BindingResult bindingResult) throws BindException {
        if (userService.validateDuplicateUser(formDto.getEmail())) {
            bindingResult.rejectValue("email", "duplicate");
        }
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        UserDto userDto = new UserDto(formDto.getEmail(), formDto.getPassword(), formDto.getUserName());
        userService.signup(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

