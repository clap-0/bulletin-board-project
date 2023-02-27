package com.soo0.bulletin_board.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * 패키지 내에서 발생한 예외 클래스를 처리하는 컨트롤러 어드바이스이다.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {
    private final MessageSource messageSource;

    /**
     * ModelAttribute 어노테이션으로 binding error 발생으로 인한 BindException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception BindException 객체
     * @param locale 지역 정보를 담은 Locale 객체
     * @return INVALID_INPUT_VALUE 에러 코드와 에러 메시지, 필드 에러를 담은 ErrorResponse 객체
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException exception, Locale locale) {
        return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, "Invalid input value",
                exception.getBindingResult(), messageSource, locale);
    }

    /**
     * 회원가입 도중 이미 존재하는 ID(이메일)를 사용하려 할 때 발생하는
     * DuplicateUserException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception DuplicateUserException 객체
     * @return DuplicateUserException 예외 클래스에 대한 ErrorResponse 객체
     */
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicateUserException(DuplicateUserException exception) {
        return new ErrorResponse(exception);
    }

    /**
     * 존재하지 않는 사용자에 접근할 때 발생하는 UserNotFoundException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception UserNotFoundException 객체
     * @return UserNotFoundException 예외 클래스에 대한 ErrorResponse 객체
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    /**
     * 존재하지 않는 게시판에 접근할 때 발생하는 BoardNotFoundException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception BoardNotFoundException 객체
     * @return BoardNotFoundException 예외 클래스에 대한 ErrorResponse 객체
     */
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBoardNotFoundException(BoardNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    /**
     * 존재하지 않는 게시글에 접근할 때 발생하는 PostNotFoundException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception PostNotFoundException 객체
     * @return PostNotFoundException 예외 클래스에 대한 ErrorResponse 객체
     */
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePostNotFoundException(PostNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    /**
     * 데이터 생성/수정/삭제 작업이 실패한 경우 발생하는 DataModificationException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception DataModificationException 객체
     * @return DataModificationException 예외 클래스에 대한 ErrorResponse 객체
     */
    @ExceptionHandler(DataModificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataModificationException(DataModificationException exception) {
        return new ErrorResponse(exception);
    }

    /**
     * 로그인 요청에서 주어진 사용자 정보와 일치하는 사용자가
     * 데이터베이스에 없으면 발생하는 BadCredentialsException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception BadCredentialsException 객체
     * @return LOGIN_FAILED 에러 코드와 BadCredentialsException 객체의 에러 메시지를 포함하는 ErrorResponse 객체
     */
    @ExceptionHandler(BadCredentialsException.class)    // TODO - 스프링 시큐리티를 배우면 수정할 것!
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(BadCredentialsException exception) {
        String message = exception.getMessage();
        return new ErrorResponse(ErrorCode.LOGIN_FAILED, message);
    }

    /**
     * 다른 사용자의 게시글, 댓글, 사용자 정보를 변경하려 하거나
     * 관리자 권한이 필요한 작업을 수행하려 할 때 발생하는 AccessDeniedException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception AccessDeniedException 객체
     * @return ACCESS_DENIED 에러 코드와 AccessDeniedException 객체의 에러 메시지를 포함하는 ErrorResponse 객체
     */
    @ExceptionHandler(AccessDeniedException.class)      // TODO - 스프링 시큐리티를 배우면 수정할 것!
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
        String message = exception.getMessage();
        return new ErrorResponse(ErrorCode.ACCESS_DENIED, message);
    }

    /**
     * 인증이 필요한 서비스에 인증되지 않은 사용자가 접근하려고 할 때 발생하는
     * InsufficientAuthenticationException 예외 클래스를 처리하는 메서드이다.
     *
     * @param exception InsufficientAuthenticationException
     * @return UNAUTHORIZED 에러 코드와 InsufficientAuthenticationException 객체의 에러 메시지를 포함하는 ErrorResponse 객체
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInsufficientAuthenticationException(InsufficientAuthenticationException exception) {
        String message = exception.getMessage();
        return new ErrorResponse(ErrorCode.UNAUTHORIZED, message);
    }
}
