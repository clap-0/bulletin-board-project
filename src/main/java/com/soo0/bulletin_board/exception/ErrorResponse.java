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

@Getter
public class ErrorResponse {
    private final String code;
    private final int status;
    private final String message;
    private List<FieldErrorDetail> fieldErrors;

    public ErrorResponse(ErrorCodeSupport exception) {
        this(exception.getErrorCode(), exception.getMessage());
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = message;
        this.fieldErrors = new ArrayList<>();
    }

    public ErrorResponse(ErrorCode errorCode, String message, Errors fieldErrors,
                            MessageSource messageSource, Locale locale) {
        this(errorCode, message);
        this.fieldErrors = fieldErrors.getFieldErrors()
                        .stream()
                        .map(error -> FieldErrorDetail.create(error, messageSource, locale))
                        .collect(Collectors.toList());
    }

    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldErrorDetail {
        String objectName;
        String field;
        String code;
        String message;

        public static FieldErrorDetail create(FieldError fieldError, MessageSource messageSource, Locale locale) {
            return new FieldErrorDetail(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode(),
                        messageSource.getMessage(fieldError, locale));
        }
    }
}
