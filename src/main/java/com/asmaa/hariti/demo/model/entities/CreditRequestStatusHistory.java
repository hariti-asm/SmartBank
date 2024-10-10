package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

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

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CreditRequestStatusHistory() {
    }

    public CreditRequestStatusHistory(CreditRequest creditRequest, CreditStatus status, LocalDateTime updatedAt) {
        this.creditRequest = creditRequest;
        this.status = status;
        this.updatedAt = updatedAt;
    }


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


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}