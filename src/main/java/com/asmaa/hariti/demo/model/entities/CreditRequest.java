package com.asmaa.hariti.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "credit_request_status",
            joinColumns = @JoinColumn(name = "credit_request_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id")
    )
    private Set<CreditStatus> statuses = new HashSet<>();

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


    public CreditRequest(String firstName, String lastName, String cin, LocalDate birthdate, LocalDate workDate,
                         BigDecimal revenues, LocalDate requestDate, String phone, String email, String job,
                         Set<CreditStatus> statuses, Integer duration, BigDecimal monthlyPayment, BigDecimal folderCost) {
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
        this.statuses = statuses != null ? statuses : new HashSet<>();
        this.duration = duration;
        this.monthlyPayment = monthlyPayment;
        this.folderCost = folderCost;
    }
    public void addStatus(CreditStatus status) {
        if (this.statuses == null) {
            this.statuses = new HashSet<>();
        }
        this.statuses.add(status);
    }
    public CreditRequest() {
        this.statuses = new HashSet<>();
    }

    public void removeStatus(CreditStatus status) {
        if (this.statuses != null) {
            this.statuses.remove(status);
        }
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

    public Set<CreditStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<CreditStatus> statuses) {
        this.statuses = statuses;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getFolderCost() {
        return folderCost;
    }

    public void setFolderCost(BigDecimal folderCost) {
        this.folderCost = folderCost;
    }
    }

