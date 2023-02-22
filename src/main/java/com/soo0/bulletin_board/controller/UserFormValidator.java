package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.UserFormDto;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserFormDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormDto dto = (UserFormDto) target;
        String password = dto.getPassword();
        String passwordConfirmation = dto.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            errors.rejectValue("password", "NotConfirmed");
        }
    }
}
