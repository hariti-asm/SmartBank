package com.asmaa.hariti.demo.dao.repositories;

import com.asmaa.hariti.demo.model.entities.CreditStatus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public interface CreditStatusDAO {
    CreditStatus save(CreditStatus creditStatus);
    Optional<CreditStatus> getCreditStatus(String creditStatusId);
    List<CreditStatus> getAllCreditStatuses();
    void deleteCreditStatus(String creditStatusId);
    void updateCreditStatus(CreditStatus creditStatus);
}