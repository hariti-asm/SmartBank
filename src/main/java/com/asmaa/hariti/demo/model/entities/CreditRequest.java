package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credit_requests")
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100, message = "First name must not exceed 100 characters")
    @Column(name = "first_name", nullable = true, length = 100)
    private String firstName;

    @Size(max = 100, message = "Last name must not exceed 100 characters")
    @Column(name = "last_name", nullable = true, length = 100)
    private String lastName;

    @Size(max = 20, message = "CIN must not exceed 20 characters")
    @Column(name = "cin", nullable = true, length = 20)
    private String cin;

    @Past(message = "Birthdate must be in the past")
    @Column(name = "birthdate", nullable = true)
    private LocalDate birthdate;

    @PastOrPresent(message = "Work date must not be in the future")
    @Column(name = "work_date", nullable = true)
    private LocalDate workDate;

    @NotBlank(message = "Job is required")
    @Column(name = "job", nullable = false)
    private String job;

    @PositiveOrZero(message = "Revenues must be positive or zero")
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal revenues;

    @PastOrPresent(message = "Request date must not be in the future")
    @Column(name = "request_date", nullable = true)
    private LocalDate requestDate;

    @Positive(message = "Amount must be positive")
    @Column(name = "amount", nullable = true, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }

    @OneToMany(mappedBy = "creditRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditRequestStatusHistory> statusHistory = new ArrayList<>();

    @Pattern(regexp = "^(07|06)\\d{8}$", message = "Phone number must start with 07 or 06 and contain exactly 10 digits")
    @Column(name = "phone", nullable = true, length = 15)
    private String phone;

    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Positive(message = "Duration must be positive")
    @Column(name = "duration", nullable = true)
    private Integer duration;

    @PositiveOrZero(message = "Monthly payment must be positive or zero")
    @Column(name = "monthly_payment", nullable = true, precision = 10, scale = 2)
    private BigDecimal monthlyPayment;

    @PositiveOrZero(message = "Folder cost must be positive or zero")
    @Column(name = "folder_cost", nullable = true, precision = 10, scale = 2)
    private BigDecimal folderCost;

    public CreditRequest() {
    }

    public CreditRequest(String firstName, String lastName, String cin, LocalDate birthdate, LocalDate workDate,
                         BigDecimal revenues, LocalDate requestDate, String phone, String email, String job,
                         Integer duration, BigDecimal monthlyPayment, BigDecimal folderCost, BigDecimal amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.birthdate = birthdate;
        this.workDate = workDate;
        this.revenues = revenues;
        this.requestDate = requestDate;
        this.phone = phone;
        this.email = email;
        this.job = job;
        this.duration = duration;
        this.monthlyPayment = monthlyPayment;
        this.folderCost = folderCost;
        this.amount = amount;
    }

    // Getters and Setters...

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(max = 100, message = "First name must not exceed 100 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(max = 100, message = "First name must not exceed 100 characters") String firstName) {
        this.firstName = firstName;
    }

    public @Size(max = 100, message = "Last name must not exceed 100 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(max = 100, message = "Last name must not exceed 100 characters") String lastName) {
        this.lastName = lastName;
    }

    public @Size(max = 20, message = "CIN must not exceed 20 characters") String getCin() {
        return cin;
    }

    public void setCin(@Size(max = 20, message = "CIN must not exceed 20 characters") String cin) {
        this.cin = cin;
    }

    public @Past(message = "Birthdate must be in the past") LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@Past(message = "Birthdate must be in the past") LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public @PastOrPresent(message = "Work date must not be in the future") LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(@PastOrPresent(message = "Work date must not be in the future") LocalDate workDate) {
        this.workDate = workDate;
    }

    public @NotBlank(message = "Job is required") String getJob() {
        return job;
    }

    public void setJob(@NotBlank(message = "Job is required") String job) {
        this.job = job;
    }

    public @PositiveOrZero(message = "Revenues must be positive or zero") BigDecimal getRevenues() {
        return revenues;
    }

    public void setRevenues(@PositiveOrZero(message = "Revenues must be positive or zero") BigDecimal revenues) {
        this.revenues = revenues;
    }

    public @PastOrPresent(message = "Request date must not be in the future") LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(@PastOrPresent(message = "Request date must not be in the future") LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public @Positive(message = "Amount must be positive") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@Positive(message = "Amount must be positive") BigDecimal amount) {
        this.amount = amount;
    }

    public List<CreditRequestStatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<CreditRequestStatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public @Pattern(regexp = "^(07|06)\\d{8}$", message = "Phone number must start with 07 or 06 and contain exactly 10 digits") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "^(07|06)\\d{8}$", message = "Phone number must start with 07 or 06 and contain exactly 10 digits") String phone) {
        this.phone = phone;
    }

    public @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @Positive(message = "Duration must be positive") Integer getDuration() {
        return duration;
    }

    public void setDuration(@Positive(message = "Duration must be positive") Integer duration) {
        this.duration = duration;
    }

    public @PositiveOrZero(message = "Monthly payment must be positive or zero") BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(@PositiveOrZero(message = "Monthly payment must be positive or zero") BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public @PositiveOrZero(message = "Folder cost must be positive or zero") BigDecimal getFolderCost() {
        return folderCost;
    }

    public void setFolderCost(@PositiveOrZero(message = "Folder cost must be positive or zero") BigDecimal folderCost) {
        this.folderCost = folderCost;
    }




    public void addStatusHistory(CreditStatus status) {
        CreditRequestStatusHistory history = new CreditRequestStatusHistory(this, status, LocalDateTime.now());
        statusHistory.add(history);
    }
}
