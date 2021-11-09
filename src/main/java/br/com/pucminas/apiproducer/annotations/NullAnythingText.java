package br.com.pucminas.apiproducer.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NullAnythingText implements ConstraintValidator<NullAnythingTextValidator, String> {

    private int length;

    @Override
    public void initialize(NullAnythingTextValidator nullAnythingTextValidator){
        this.length = nullAnythingTextValidator.length();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (null == s || s.isBlank()) return true;

        return (s.length() >= length) ;
    }
}
