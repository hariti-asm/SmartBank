package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "credit_status")
public class CreditStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<CreditRequestStatusHistory> statusHistories;


    public CreditStatus() {
    }

    public CreditStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }



    // Getters and Setters

    public List<CreditRequestStatusHistory> getStatusHistories() {
        return statusHistories;
    }

    public void setStatusHistories(List<CreditRequestStatusHistory> statusHistories) {
        this.statusHistories = statusHistories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
