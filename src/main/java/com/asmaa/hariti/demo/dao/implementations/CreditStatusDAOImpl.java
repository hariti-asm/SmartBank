package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditStatusDAO;
import com.asmaa.hariti.demo.model.entities.CreditStatus;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Dependent
public class CreditStatusDAOImpl implements CreditStatusDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Long save(Long creditStatus) {
        em.persist(creditStatus);
        return creditStatus;
    }

    @Override
    public Optional<CreditStatus> getCreditStatus(String creditStatusId) {
        return Optional.ofNullable(em.find(CreditStatus.class, creditStatusId));
    }

    @Override
    public List<CreditStatus> getAllCreditStatuses() {
        return em.createQuery("SELECT cs FROM CreditStatus cs", CreditStatus.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteCreditStatus(String creditStatusId) {
        CreditStatus creditStatus = em.find(CreditStatus.class, creditStatusId);
        if (creditStatus != null) {
            em.remove(creditStatus);
        }
    }

    @Override
    @Transactional
    public void updateCreditStatus(CreditStatus creditStatus) {
        em.merge(creditStatus);
    }
}