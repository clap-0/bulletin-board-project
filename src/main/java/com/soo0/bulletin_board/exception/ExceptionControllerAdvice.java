package com.soo0.bulletin_board.exception;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
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
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicateUserException(DuplicateUserException exception) {
        return new ErrorResponse(exception);
    }

    // 로그인 요청에서 주어진 사용자 정보와 일치하는 사용자가 데이터베이스에 없으면 UserNotFoundException 발생
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    // 존재하지 않는 게시판에 접근하면 BoardNotFoundException 발생
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBoardNotFoundException(BoardNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    // TODO - 스프링 시큐리티를 배우면 수정할 것!
    // 다른 사용자의 게시물, 댓글, 사용자 정보를 변경하려 하거나
    // 관리자 권한이 필요한 작업을 수행하려 하면 AccessDeniedException 발생
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
        String message = exception.getMessage();
        return new ErrorResponse(ErrorCode.ACCESS_DENIED, message);
    }

    // TODO - 스프링 시큐리티를 배우면 수정할 것!
    // 인증이 필요한 서비스를 호출할 때 인증되지 않은 사용자가 접근하려고 할 때 InsufficientAuthenticationException 발생
    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInsufficientAuthenticationException(InsufficientAuthenticationException exception) {
        String message = exception.getMessage();
        return new ErrorResponse(ErrorCode.UNAUTHORIZED, message);
    }
}
