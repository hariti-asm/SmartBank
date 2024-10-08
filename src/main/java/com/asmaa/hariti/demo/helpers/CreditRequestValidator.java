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

    public static List<String> validateFirstName(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "firstName");
    }

    public static List<String> validateLastName(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "lastName");
    }

    public static List<String> validateCin(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "cin");
    }

    public static List<String> validateBirthdate(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "birthdate");
    }

    public static List<String> validateWorkDate(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "workDate");
    }

    public static List<String> validateJob(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "job");
    }

    public static List<String> validateRevenues(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "revenues");
    }

    public static List<String> validateRequestDate(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "requestDate");
    }

    public static List<String> validatePhone(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "phone");
    }

    public static List<String> validateEmail(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "email");
    }

    public static List<String> validateDuration(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "duration");
    }

    public static List<String> validateMonthlyPayment(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "monthlyPayment");
    }

    public static List<String> validateFolderCost(CreditRequest creditRequest) {
        return validateProperty(creditRequest, "folderCost");
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