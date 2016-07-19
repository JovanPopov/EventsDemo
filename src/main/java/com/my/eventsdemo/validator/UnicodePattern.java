package com.my.eventsdemo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//Podrska za UNICODE, psoto tekuca Pattern antocije ne podrzava UNICODE u potpunosti. tek od JDK 1.7 je uvedena
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnicodePatternValidator.class)
@Documented
public @interface UnicodePattern {
	
  public static final String FIRST_NAME_PATTERN = "[\\p{Alpha} \\-]*";
  public static final String LAST_NAME_PATTERN = "[\\p{Alpha} \\-]*";
  public static final String PHONE_PATTERN = "[\\p{Digit} \\-\\/\\+]*";
	
	String message() default "Validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String pattern();
    
    
    //value je poseban atribut anotacije, njegov naziv moze da se izostavi kada se navodi vrednost anotacije, ali samo ako se on navodi kao jedini atribut 
    //String value();

}
