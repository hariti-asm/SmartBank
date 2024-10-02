package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CreditRequestDAOImpl implements CreditRequestDAO {
    @PersistenceContext
    private EntityManager em;
    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        em.persist(creditRequest);
        return creditRequest;
    }

    @Override
    public CreditRequest getCreditRequest(String creditRequestId) {
        return em.find(CreditRequest.class , creditRequestId);
    }

    @Override
    public List<CreditRequest> getAllCreditRequests() {
        return em.createQuery("SELECT cr FROM CreditRequest cr", CreditRequest.class).getResultList();
    }

    @Override
    public void deleteCreditRequest(String creditRequestId) {
        CreditRequest creditRequest = getCreditRequest(creditRequestId);
        if(creditRequest != null) {
            em.remove(creditRequest);
        }

    }

    @Override
    public void updateCreditRequest(CreditRequest creditRequest) {
        em.merge(creditRequest);

    }
}
