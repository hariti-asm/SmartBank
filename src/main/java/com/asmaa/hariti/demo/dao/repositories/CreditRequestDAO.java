package com.asmaa.hariti.demo.dao.repositories;

import com.asmaa.hariti.demo.model.entities.CreditRequest;

import java.util.List;

public interface CreditRequestDAO {

    CreditRequest save(CreditRequest creditRequest);
    CreditRequest getCreditRequest(String creditRequestId);
    List<CreditRequest> getAllCreditRequests();
    void deleteCreditRequest(String creditRequestId);
    void updateCreditRequest(CreditRequest creditRequest);
}
