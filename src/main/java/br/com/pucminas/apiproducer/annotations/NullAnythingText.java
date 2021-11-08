package br.com.pucminas.apiproducer.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NullAnythingText implements ConstraintValidator<NullAnythingTextValidator, String> {

    private int size;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (null == s || s.isBlank()) return true;

        return (s.length() >= size) ;
    }
}
