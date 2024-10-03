package com.asmaa.hariti.demo.helpers;

import com.asmaa.hariti.demo.model.entities.CreditRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreditRequestValidator {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static List<String> validate(CreditRequest creditRequest) {
        Set<ConstraintViolation<CreditRequest>> violations = validator.validate(creditRequest);
        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<CreditRequest> violation : violations) {
            errors.add(violation.getMessage());
        }

        return errors;
    }

    public static List<String> validateCustomerName(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "customerName");
    }

    public static List<String> validateAmount(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "amount");
    }

    public static List<String> validateRequestDate(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "requestDate");
    }

    public static List<String> validateTerm(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "term");
    }

    public static List<String> validateInterestRate(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "interestRate");
    }

    public static List<String> validatePhone(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "phone");
    }

    public static List<String> validateEmail(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "email");
    }

    private static List<String> validateProperty(CreditRequest creditRequest, String propertyName) {
        Set<ConstraintViolation<CreditRequest>> violations = validator.validateProperty(creditRequest, propertyName);
        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<CreditRequest> violation : violations) {
            errors.add(violation.getMessage());
        }

        return errors;
    }
}