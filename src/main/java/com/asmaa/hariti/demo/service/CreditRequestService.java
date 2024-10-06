package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.dao.implementations.CreditRequestDAOImpl;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@Named
public class CreditRequestService {

    private CreditRequestDAO creditRequestDAO;

    public CreditRequestService() {
    }

    @Inject
    public CreditRequestService(CreditRequestDAO creditRequestDAO) {
        this.creditRequestDAO = creditRequestDAO;
    }

    private void initializeDAO() {
        if (creditRequestDAO == null) {
            creditRequestDAO = new CreditRequestDAOImpl();
        }
    }

    public void createCreditRequest(CreditRequest creditRequest) {
        initializeDAO();
        creditRequestDAO.save(creditRequest);
    }

    public Optional<CreditRequest> getCreditRequest(Long creditRequestId) {
        initializeDAO();
        return creditRequestDAO.getCreditRequest(creditRequestId);
    }

    public List<CreditRequest> getAllCreditRequests() {
        initializeDAO();
        return creditRequestDAO.getAllCreditRequests();
    }

    public void deleteCreditRequest(Long creditRequestId) {
        initializeDAO();
        creditRequestDAO.deleteCreditRequest(creditRequestId);
    }

    public void updateCreditRequest(CreditRequest creditRequest) {
        initializeDAO();
        creditRequestDAO.updateCreditRequest(creditRequest);
    }

    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        initializeDAO();
        return creditRequestDAO.getCreditRequestsByStatus(status);
    }
}