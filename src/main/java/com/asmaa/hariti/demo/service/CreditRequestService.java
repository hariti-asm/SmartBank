package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@Named
public class CreditRequestService {

    private final CreditRequestDAO creditRequestDAO;

    @Inject
    public CreditRequestService(CreditRequestDAO creditRequestDAO) {
        this.creditRequestDAO = creditRequestDAO;
    }

    public void createCreditRequest(CreditRequest creditRequest) {
        creditRequestDAO.save(creditRequest);
    }

    public Optional<CreditRequest> getCreditRequest(String creditRequestId) {
        return creditRequestDAO.getCreditRequest(creditRequestId);
    }

    public List<CreditRequest> getAllCreditRequests() {
        return creditRequestDAO.getAllCreditRequests();
    }

    public void deleteCreditRequest(String creditRequestId) {
        creditRequestDAO.deleteCreditRequest(creditRequestId);
    }

    public void updateCreditRequest(CreditRequest creditRequest) {
        creditRequestDAO.updateCreditRequest(creditRequest);
    }

    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        return creditRequestDAO.getCreditRequestsByStatus(status);
    }
}