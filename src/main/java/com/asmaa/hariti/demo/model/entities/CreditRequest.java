package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_requests")
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "CIN is required")
    @Size(max = 20, message = "CIN must not exceed 20 characters")
    @Column(name = "cin", nullable = false, length = 20)
    private String cin;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate must be in the past")
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @NotNull(message = "Work date is required")
    @PastOrPresent(message = "Work date must not be in the future")
    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @NotNull(message = "Revenues are required")
    @PositiveOrZero(message = "Revenues must be positive or zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal revenues;

    @NotNull(message = "Request date is required")
    @PastOrPresent(message = "Request date must not be in the future")
    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @NotNull(message = "Term is required")
    @Positive(message = "Term must be positive")
    @Column(nullable = false)
    private Integer term;

    @NotNull(message = "Interest rate is required")
    @PositiveOrZero(message = "Interest rate must be positive or zero")
    @DecimalMax(value = "100.00", message = "Interest rate must not exceed 100%")
    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @NotBlank(message = "Status is required")
    @Column(nullable = false, length = 20)
    private String status;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(07|06)\\d{8}$", message = "Phone number must start with 07 or 06 and contain exactly 10 digits")
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, length = 100)
    private String email;


    public CreditRequest() {
        this.status = "PENDING";
    }

    public CreditRequest(String firstName, String lastName, String cin, LocalDate birthdate, LocalDate workDate, BigDecimal revenues, LocalDate requestDate, Integer term, BigDecimal interestRate, String phone, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.birthdate = birthdate;
        this.workDate = workDate;
        this.revenues = revenues;
        this.requestDate = requestDate;
        this.term = term;
        this.interestRate = interestRate;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public BigDecimal getRevenues() {
        return revenues;
    }

    public void setRevenues(BigDecimal revenues) {
        this.revenues = revenues;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void approve() {
        this.status = "APPROVED";
    }

    public void reject() {
        this.status = "REJECTED";
    }
}
