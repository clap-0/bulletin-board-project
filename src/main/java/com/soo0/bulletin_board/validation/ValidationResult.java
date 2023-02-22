package com.soo0.bulletin_board.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/*
 * Spring BindingResult를 json으로 받기
 * https://meetup.nhncloud.com/posts/147
* */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult {
    List<FieldErrorDetail> errors;

    public static ValidationResult create(Errors errors, MessageSource messageSource, Locale locale) {
        List<FieldErrorDetail> details =
                errors.getFieldErrors()
                        .stream()
                        .map(error -> FieldErrorDetail.create(error, messageSource, locale))
                        .collect(Collectors.toList());
        return new ValidationResult(details);
    }
}