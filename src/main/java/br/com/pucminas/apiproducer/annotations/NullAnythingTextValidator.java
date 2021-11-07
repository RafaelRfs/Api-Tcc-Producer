package br.com.pucminas.apiproducer.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {NullAnythingText.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface NullAnythingTextValidator {

    int size();

    String message() default "Texto invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
