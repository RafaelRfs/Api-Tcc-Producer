package br.com.pucminas.apiproducer.annotations;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class NullAnythingText implements ConstraintValidator<NullAnythingTextValidator, String> {

    private int size;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (null == s || s.isBlank()) || (s.length() >= size) ;
    }
}
