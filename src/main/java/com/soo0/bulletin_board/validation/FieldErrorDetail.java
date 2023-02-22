package com.soo0.bulletin_board.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.Locale;

/*
 * Spring BindingResult를 json으로 받기
 * https://meetup.nhncloud.com/posts/147
 * */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldErrorDetail {
    String objectName;
    String field;
    String code;
    String message;

    public static FieldErrorDetail create(FieldError fieldError, MessageSource messageSource, Locale locale) {
        return new FieldErrorDetail(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getCode(),
                messageSource.getMessage(fieldError, locale)); // 이 부분이 포인트
    }
}