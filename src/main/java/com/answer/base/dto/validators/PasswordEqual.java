package com.answer.base.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {
    String message() default "password are not equal";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
