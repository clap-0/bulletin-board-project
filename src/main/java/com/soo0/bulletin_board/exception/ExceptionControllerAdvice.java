package com.soo0.bulletin_board.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {
    private final MessageSource messageSource;

    // @ModelAttribute 로 binding error 발생 시 BindException 발생
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException exception, Locale locale) {
        return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, "Invalid input value",
                    exception.getBindingResult(), messageSource, locale);
    }

    // 회원가입 도중 이미 존재하는 ID(이메일)을 사용하려 할 시 DuplicateUserException 발생
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicateUserException(DuplicateUserException exception) {
        return new ErrorResponse(exception);
    }
}
