package com.answer.base.dto.validators;

import com.answer.base.dto.UserDTO;
import com.answer.base.dto.UserRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, UserRegisterDTO> {
    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password = userRegisterDTO.getPassword();
        String password2 = userRegisterDTO.getPassword2();
        return password.equals(password2);
    }

}
