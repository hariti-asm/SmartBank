package com.asmaa.hariti.demo.dao.repositories;

import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;

import java.util.List;
import java.util.Optional;

public interface CreditRequestDAO {

    CreditRequest save(CreditRequest creditRequest);
    Optional<CreditRequest> getCreditRequest(String creditRequestId);
    List<CreditRequest> getAllCreditRequests();
    void deleteCreditRequest(String creditRequestId);
    void updateCreditRequest(CreditRequest creditRequest);
    List<CreditRequest> getCreditRequestsByStatus(CreditStatus status);
}
