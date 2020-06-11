package com.answer.base.dto.validators;

import com.answer.base.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, UserDTO> {
    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password = userDTO.getPassword();
        String password2 = userDTO.getPassword2();
        return password.equals(password2);
    }
}
