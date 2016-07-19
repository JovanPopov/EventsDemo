package com.my.eventsdemo.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicodePatternValidator implements ConstraintValidator<UnicodePattern, String> {
	
	private Pattern pattern;

	@Override
	public void initialize(UnicodePattern unicodePattern) {
		//Opcija Pattern.UNICODE_CHARACTER_CLASS uvedena je tek u JDK 1.7
		pattern = Pattern.compile(unicodePattern.pattern(), Pattern.UNICODE_CHARACTER_CLASS);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.equals(""))
			return true;
		
		return pattern.matcher(value).matches();
	}
	
	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
	public void setPattern(String pattern) {
		this.pattern = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
	}

}
