package com.asmaa.hariti.demo.dao.repositories;

import com.asmaa.hariti.demo.model.entities.CreditStatus;

import java.util.List;
import java.util.Optional;

public interface CreditStatusDAO {
    CreditStatus save(CreditStatus creditStatus);
    Optional<CreditStatus> getCreditStatus(String CreditStatusId) ;
    List<CreditStatus> getAllCreditRequests();
    void deleteCreditStatus (String CreditStatusId);
    void updateCreditStatus (CreditStatus  CreditStatus );

}
