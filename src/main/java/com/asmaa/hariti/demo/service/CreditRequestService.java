package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreditRequestService {

    @Inject
    private CreditRequestDAO creditRequestDAO;

    public CreditRequestService() {}
    @Transactional
    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        BigDecimal amount = creditRequest.getAmount();
        BigDecimal duration = BigDecimal.valueOf(creditRequest.getDuration());

        BigDecimal monthlyPayment = calculateMonthlyPayment(amount, duration);

        creditRequest.setMonthlyPayment(monthlyPayment);

        return creditRequestDAO.save(creditRequest);
    }
    private BigDecimal calculateMonthlyPayment(BigDecimal capital, BigDecimal durationInMonths) {
        final BigDecimal ANNUAL_RATE = new BigDecimal("0.12");
        final BigDecimal MONTHS_IN_YEAR = new BigDecimal("12");

        if (capital == null || durationInMonths == null || capital.compareTo(BigDecimal.ZERO) <= 0 || durationInMonths.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal monthlyRate = ANNUAL_RATE.divide(MONTHS_IN_YEAR, 10, RoundingMode.HALF_UP);

        // Calculate (1 + monthlyRate)^(-n)
        BigDecimal base = BigDecimal.ONE.add(monthlyRate);
        BigDecimal exponent = durationInMonths.negate();
        BigDecimal powerTerm = base.pow(exponent.intValue(), new MathContext(10));

        // Calculate monthly payment applying: (K * monthlyRate) / (1 - (1 + monthlyRate)^(-n))
        BigDecimal numerator = capital.multiply(monthlyRate);
        BigDecimal denominator = BigDecimal.ONE.subtract(powerTerm);

        BigDecimal monthlyPayment = numerator.divide(denominator, 2, RoundingMode.HALF_UP);

        return monthlyPayment;
    }

    public Optional<CreditRequest> getCreditRequest(Long creditRequestId) {
        return creditRequestDAO.getCreditRequest(creditRequestId);
    }

    public List<CreditRequest> getAllCreditRequests() {
        return creditRequestDAO.getAllCreditRequests();
    }

    @Transactional
    public void deleteCreditRequest(Long creditRequestId) {
        creditRequestDAO.deleteCreditRequest(creditRequestId);
    }

    @Transactional
    public void updateCreditRequest(CreditRequest creditRequest) {
        creditRequestDAO.updateCreditRequest(creditRequest);
    }

    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        return creditRequestDAO.getCreditRequestsByStatus(status);
    }

    @Transactional
    public void addStatusToCreditRequest(Long creditRequestId, CreditStatus newStatus) {
        creditRequestDAO.addStatusToCreditRequest(creditRequestId, newStatus);
    }

    public CreditStatus getCurrentStatus(Long creditRequestId) {
        return getCreditRequest(creditRequestId)
                .map(creditRequest -> {
                    var statusHistory = creditRequest.getStatusHistory();
                    return statusHistory.isEmpty() ? null : statusHistory.get(statusHistory.size() - 1).getStatus();
                })
                .orElse(null);
    }
}