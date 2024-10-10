package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.dao.implementations.CreditRequestDAOImpl;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

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

    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        initializeDAO();
        return creditRequestDAO.save(creditRequest);
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

    @Transactional
    public void updateCreditRequest(CreditRequest creditRequest) {
        creditRequestDAO.updateCreditRequest(creditRequest);
    }

    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        initializeDAO();
        return creditRequestDAO.getCreditRequestsByStatus(status);
    }

    public void addStatusToCreditRequest(Long creditRequestId, CreditStatus newStatus) {
        initializeDAO();
        if (creditRequestDAO instanceof CreditRequestDAOImpl) {
            ((CreditRequestDAOImpl) creditRequestDAO).addStatusToCreditRequest(creditRequestId, newStatus);
        } else {
            throw new UnsupportedOperationException("This operation is not supported by the current DAO implementation");
        }
    }

    public CreditStatus getCurrentStatus(Long creditRequestId) {
        initializeDAO();
        Optional<CreditRequest> creditRequest = getCreditRequest(creditRequestId);
        return creditRequest.map(cr -> {
            var statusHistory = cr.getStatusHistory();
            return statusHistory.isEmpty() ? null : statusHistory.get(statusHistory.size() - 1).getStatus();
        }).orElse(null);
    }
}