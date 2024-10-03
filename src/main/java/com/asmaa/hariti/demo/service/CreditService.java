package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;

public class CreditService {
    private final CreditRequestDAO creditRequestDAO;

    public CreditService(CreditRequestDAO creditRequestDAO) {
        this.creditRequestDAO = creditRequestDAO;
    }
    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        return this.creditRequestDAO.save(creditRequest);
    }
    public CreditRequest updateCreditRequest(CreditRequest creditRequest) {
        return this.creditRequestDAO.save(creditRequest);
    }
    public void deleteCreditRequest(CreditRequest creditRequest) {
        creditRequestDAO.deleteCreditRequest(creditRequest.getId());
    }
    public CreditRequest getCreditRequest(CreditRequest creditRequest) {
        return creditRequestDAO.save(creditRequest);
    }
}
