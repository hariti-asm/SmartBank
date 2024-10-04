package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.dao.repositories.CreditStatusDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;

public class CreditStatusService {


    private final CreditStatusDAO creditRequestStatusDAO;

    public CreditStatusService(CreditStatusDAO creditRequestStatusDAO) {
        this.creditRequestStatusDAO = creditRequestStatusDAO;
    }

    public CreditStatus createCreditStatus(CreditStatus CreditStatus) {
        return this.creditRequestStatusDAO.save(CreditStatus);
    }
    public CreditStatus updateCreditStatus(CreditStatus CreditStatus) {
        return this.creditRequestStatusDAO.save(CreditStatus);
    }
    public void deleteCreditStatus(CreditStatus CreditStatus) {
         this.creditRequestStatusDAO.deleteCreditStatus(CreditStatus.getId());
    }
    public CreditStatus getCreditStatus(CreditStatus CreditStatus) {
         return this.creditRequestStatusDAO.save(CreditStatus);
    }}
