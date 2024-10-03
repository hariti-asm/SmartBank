package com.asmaa.hariti.demo.helpers;

import com.asmaa.hariti.demo.model.entities.CreditRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreditRequestValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(07|06)\\d{8}$"); // Regex for phone validation

    public static List<String> validate(CreditRequest creditRequest) {
        List<String> errors = new ArrayList<>();

        validateCustomerName(creditRequest.getCustomerName(), errors);
        validateAmount(creditRequest.getAmount(), errors);
        validateRequestDate(creditRequest.getRequestDate(), errors);
        validateTerm(creditRequest.getTerm(), errors);
        validateInterestRate(creditRequest.getInterestRate(), errors);
        validatePhone(creditRequest.getPhone(), errors);
        validateEmail(creditRequest.getEmail(), errors);

        return errors;
    }

    private static void validateCustomerName(String customerName, List<String> errors) {
        if (customerName == null || customerName.trim().isEmpty()) {
            errors.add("Customer name is required");
        } else if (customerName.length() > 100) {
            errors.add("Customer name must not exceed 100 characters");
        }
    }

    private static void validateAmount(BigDecimal amount, List<String> errors) {
        if (amount == null) {
            errors.add("Amount is required");
        } else if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Amount must be greater than zero");
        } else if (amount.compareTo(new BigDecimal("1000000")) > 0) {
            errors.add("Amount must not exceed 1,000,000");
        }
    }

    private static void validateRequestDate(LocalDate requestDate, List<String> errors) {
        if (requestDate == null) {
            errors.add("Request date is required");
        } else if (requestDate.isAfter(LocalDate.now())) {
            errors.add("Request date cannot be in the future");
        }
    }

    private static void validateTerm(Integer term, List<String> errors) {
        if (term == null) {
            errors.add("Term is required");
        } else if (term < 1 || term > 360) {
            errors.add("Term must be between 1 and 360 months");
        }
    }

    private static void validateInterestRate(BigDecimal interestRate, List<String> errors) {
        if (interestRate == null) {
            errors.add("Interest rate is required");
        } else if (interestRate.compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Interest rate cannot be negative");
        } else if (interestRate.compareTo(new BigDecimal("100")) > 0) {
            errors.add("Interest rate cannot exceed 100%");
        }
    }

    private static void validatePhone(String phone, List<String> errors) {
        if (phone == null || phone.trim().isEmpty()) {
            errors.add("Phone number is required");
        } else if (!PHONE_PATTERN.matcher(phone).matches()) {
            errors.add("Phone number must start with 07 or 06 and contain exactly 10 digits");
        }
    }

    private static void validateEmail(String email, List<String> errors) {
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email is required");
        } else if (!isValidEmail(email)) {
            errors.add("Invalid email format");
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
