package com.asmaa.hariti.demo.dao.repositories;

import com.asmaa.hariti.demo.model.entities.CreditStatus;

import java.util.List;
import java.util.Optional;

public interface CreditStatusDAO {
    Long save(Long creditStatus);
    Optional<CreditStatus> getCreditStatus(String creditStatusId);
    List<CreditStatus> getAllCreditStatuses();
    void deleteCreditStatus(String creditStatusId);
    void updateCreditStatus(CreditStatus creditStatus);
}