package br.com.pucminas.apiproducer.annotations;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidatorDomain implements ConstraintValidator<EmailValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return  (null == value || value.trim().isEmpty()) ? false : Pattern.matches(ApiConstants.EMAIL_REGEX, value);
    }
}