package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "credit_status")
public class CreditStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Status name is required")
    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    // Constructors
    public CreditStatus() {}

    public CreditStatus(String name) {
        this.name = name;
    }

    public CreditStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
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