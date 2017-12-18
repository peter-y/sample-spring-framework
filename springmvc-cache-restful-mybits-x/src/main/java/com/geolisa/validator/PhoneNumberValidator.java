package com.geolisa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {


    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.matches("\\d{11}")) {
            //validate phone numbers of format "1234567890"
            return true;
        } else if (value.matches("\\d{3}[-\\.\\s]\\d{4}[-\\.\\s]\\d{4}")) {
            //validating phone number with -, . or spaces
            return true;
        } else {
            //return false if nothing matches the input
            return false;
        }
    }
}
