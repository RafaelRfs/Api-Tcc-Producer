package br.com.pucminas.apiproducer.annotations;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class NullAnythingText implements ConstraintValidator<NullAnythingTextValidator, String> {

    private int size;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (null == s || s.isBlank()) return true;

        log.error("Valor: {} , SIZE: {} ", s, s.length());

        if (s.length() < size) return false;

        return false;
    }
}
