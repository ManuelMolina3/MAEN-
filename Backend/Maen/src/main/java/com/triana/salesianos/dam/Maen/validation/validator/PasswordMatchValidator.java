package com.triana.salesianos.dam.Maen.validation.validator;

import com.triana.salesianos.dam.Maen.validation.annotation.PasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

public class PasswordMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    private String passwordField;
    private String verifyPasswordField;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        passwordField = constraintAnnotation.passwordField();
        verifyPasswordField  = constraintAnnotation.verifyPasswordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String password = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(passwordField);

        String verifyPassword = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(verifyPasswordField);

        return StringUtils.hasText(password) && password.contentEquals(verifyPassword);
    }
}
