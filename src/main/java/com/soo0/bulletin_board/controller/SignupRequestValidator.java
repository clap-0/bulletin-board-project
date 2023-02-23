package com.soo0.bulletin_board.controller;

import com.soo0.bulletin_board.domain.dto.SignupRequest;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
public class SignupRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest dto = (SignupRequest) target;
        String password = dto.getPassword();
        String passwordConfirmation = dto.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            errors.rejectValue("password", "NotConfirmed");
        }
    }
}
