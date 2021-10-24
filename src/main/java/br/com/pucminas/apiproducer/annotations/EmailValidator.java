package br.com.pucminas.apiproducer.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {EmailValidatorDomain.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface EmailValidator {
    String message() default "Email invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
