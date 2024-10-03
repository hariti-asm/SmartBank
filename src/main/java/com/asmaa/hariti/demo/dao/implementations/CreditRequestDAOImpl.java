package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CreditRequestDAOImpl implements CreditRequestDAO {
    @PersistenceContext
    private EntityManager em;

    public CreditRequestDAOImpl() {
        this.em = EntityManagerSingleton.getEntityManager();
    }

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        try {
            em.persist(creditRequest);
            return creditRequest;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CreditRequest> getCreditRequest(String creditRequestId) {
        CreditRequest creditRequest = em.find(CreditRequest.class, creditRequestId);
        return Optional.ofNullable(creditRequest);
    }

    @Override
    public List<CreditRequest> getAllCreditRequests() {
        return em.createQuery("SELECT cr FROM CreditRequest cr", CreditRequest.class).getResultList();
    }

    @Override
    public void deleteCreditRequest(String creditRequestId) {
        try {
            em.getTransaction().begin();
            Optional<CreditRequest> creditRequest = getCreditRequest(creditRequestId);
            if (creditRequest != null) {
                em.remove(creditRequest);
            } else {
                System.out.println("CreditRequest not found for ID: " + creditRequestId);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateCreditRequest(CreditRequest creditRequest) {
        if (creditRequest == null) {
            throw new IllegalArgumentException("CreditRequest cannot be null");
        }

        try {
            em.getTransaction().begin();
            em.merge(creditRequest);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
