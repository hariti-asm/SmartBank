package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_request_status_history")
public class CreditRequestStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_request_id", nullable = false)
    private CreditRequest creditRequest;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private CreditStatus status;

    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;

    public CreditRequestStatusHistory() {
    }

    public CreditRequestStatusHistory(CreditRequest creditRequest, CreditStatus status, LocalDateTime changeDate) {
        this.creditRequest = creditRequest;
        this.status = status;
        this.changeDate = changeDate;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public CreditStatus getStatus() {
        return status;
    }

    public void setStatus(CreditStatus status) {
        this.status = status;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }
}