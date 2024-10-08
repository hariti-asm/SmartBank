package com.asmaa.hariti.demo.service;

import com.asmaa.hariti.demo.dao.repositories.CreditStatusDAO;
import com.asmaa.hariti.demo.model.entities.CreditStatus;

public class CreditStatusService {


    private CreditStatusDAO creditRequestStatusDAO = null;
    public void CreditRequestService() {
    }
    public CreditStatusService() {
        this.creditRequestStatusDAO = creditRequestStatusDAO;
    }

    public Long createCreditStatus(CreditStatus CreditStatus) {
        return this.creditRequestStatusDAO.save(CreditStatus.getId());
    }
    public Long updateCreditStatus(CreditStatus CreditStatus) {
        return this.creditRequestStatusDAO.save(CreditStatus.getId());
    }
    public void deleteCreditStatus(CreditStatus CreditStatus) {
         this.creditRequestStatusDAO.deleteCreditStatus(String.valueOf(CreditStatus.getId()));
    }
    public Long getCreditStatus(Long CreditStatus) {
         return this.creditRequestStatusDAO.save(CreditStatus);
    }}
