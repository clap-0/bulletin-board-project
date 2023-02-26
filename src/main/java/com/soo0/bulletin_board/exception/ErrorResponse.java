package com.soo0.bulletin_board.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 에러가 발생했을 때 응답에 사용하는 클래스이다.
 */
@Getter
public class ErrorResponse {
    /**
     * 발생한 에러의 에러 코드 문자열
     */
    private final String code;

    /**
     * 발생한 에러에 관한 HTTP 상태 코드
     */
    private final int status;

    /**
     * 발생한 에러에 관한 에러 메시지
     */
    private final String message;

    /**
     * 발생한 필드 에러,
     * 필드 에러가 발생하지 않은 경우 빈 배열
     */
    private List<FieldErrorDetail> fieldErrors;

    /**
     * ErrorResponse 클래스의 생성자이다.
     *
     * @param exception 발생한 예외 클래스, ErrorCodeSupport 인터페이스를 구현해야 함
     */
    public ErrorResponse(ErrorCodeSupport exception) {
        this(exception.getErrorCode(), exception.getMessage());
    }

    /**
     * ErrorResponse 클래스의 생성자이다.
     *
     * @param errorCode 발생한 에러에 관한 에러 코드 객체
     * @param message 발생한 에러에 관한 에러 메시지
     */
    public ErrorResponse(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = message;
        this.fieldErrors = new ArrayList<>();
    }

    /**
     * ErrorResponse 클래스의 생성자이다.
     *
     * @param errorCode 발생한 에러에 관한 에러 코드 객체
     * @param message 발생한 에러에 관한 에러 메시지
     * @param fieldErrors 발생한 필드 에러들을 저장하는 Errors 객체
     * @param messageSource 메시지 국제화에 사용되는 MessageSource 객체
     * @param locale 지역 정보를 담은 Locale 객체
     */
    public ErrorResponse(ErrorCode errorCode, String message, Errors fieldErrors,
                            MessageSource messageSource, Locale locale) {
        this(errorCode, message);

        // fieldErrors 파라미터를 List<FieldErrorDetail> 객체로 변환하여 저장
        this.fieldErrors = fieldErrors.getFieldErrors()
                        .stream()
                        .map(error -> FieldErrorDetail.create(error, messageSource, locale))
                        .collect(Collectors.toList());
    }

    /**
     * 필드 에러 정보를 저장하는 클래스이다.
     */
    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldErrorDetail {
        /**
         * 필드 에러가 발생한 객체의 이름
         */
        String objectName;

        /**
         * 에러가 발생한 필드
         */
        String field;

        /**
         * 발생한 필드 에러에 관한 에러 코드
         */
        String code;

        /**
         * 발생한 필드 에러에 관한 에러 메시지
         */
        String message;

        /**
         * FieldError 객체를 통해 FieldErrorDetail 객체를 생성하는 메서드이다.
         *
         * @param fieldError 필드 에러 정보를 담은 FieldError 객체
         * @param messageSource 메시지 국제화에 사용되는 MessageSource 객체
         * @param locale 지역 정보를 담은 Locale 객체
         * @return 생성된 FieldErrorDetail 객체
         */
        public static FieldErrorDetail create(FieldError fieldError, MessageSource messageSource, Locale locale) {
            return new FieldErrorDetail(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode(),
                        messageSource.getMessage(fieldError, locale));
        }
    }
}
