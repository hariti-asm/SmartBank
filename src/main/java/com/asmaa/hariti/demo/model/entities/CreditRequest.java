package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_requests")
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(nullable = false)
    private Integer term;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditStatus status;

    public CreditRequest() {}

    public CreditRequest(String customerName, BigDecimal amount, LocalDate requestDate, Integer term, BigDecimal interestRate) {
        this.customerName = customerName;
        this.amount = amount;
        this.requestDate = requestDate;
        this.term = term;
        this.interestRate = interestRate;
        this.status = CreditStatus.PENDING;
    }


    public void approve() {
        this.status = CreditStatus.APPROVED;
    }

    public void reject() {
        this.status = CreditStatus.REJECTED;
    }


    public enum CreditStatus {
        PENDING, APPROVED, REJECTED
    }
}