package com.ntt.questionados.config.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AlphanumericWithoutWhiteSpacesValidator.class)
public @interface AlphanumericWithoutWhiteSpaces {

	String message() default "{com.ntt.questionados.config.validation.AlphanumericWithoutWhiteSpaces.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
