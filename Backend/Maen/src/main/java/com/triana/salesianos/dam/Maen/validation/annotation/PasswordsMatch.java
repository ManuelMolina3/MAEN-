package com.triana.salesianos.dam.Maen.validation.annotation;

import com.triana.salesianos.dam.Maen.validation.validator.PasswordMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordsMatch {
    String message() default "The entered passwords do not match";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String passwordField();
    String verifyPasswordField();
}
