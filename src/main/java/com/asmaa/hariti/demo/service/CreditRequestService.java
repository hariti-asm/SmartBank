package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreditRequestService {

    @Inject
    private CreditRequestDAO creditRequestDAO;

    // Default no-arg constructor
    public CreditRequestService() {}

    @Transactional
    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        return creditRequestDAO.save(creditRequest);
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