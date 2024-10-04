package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreditRequestDAOImpl implements CreditRequestDAO {

    private EntityManager getEntityManager() {
        return EntityManagerSingleton.getEntityManager();
    }

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(creditRequest);
            em.getTransaction().commit();
            return creditRequest;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<CreditRequest> getCreditRequest(String creditRequestId) {
        EntityManager em = getEntityManager();
        return Optional.ofNullable(em.find(CreditRequest.class, creditRequestId));
    }

    @Override
    public List<CreditRequest> getAllCreditRequests() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT cr FROM CreditRequest cr", CreditRequest.class).getResultList();
    }

    @Override
    public void deleteCreditRequest(String creditRequestId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CreditRequest creditRequest = em.find(CreditRequest.class, creditRequestId);
            if (creditRequest != null) {
                em.remove(creditRequest);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateCreditRequest(CreditRequest creditRequest) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(creditRequest);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT cr FROM CreditRequest cr WHERE cr.status = :status", CreditRequest.class)
                .setParameter("status", status)
                .getResultList();
    }
}
