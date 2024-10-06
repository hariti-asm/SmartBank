package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
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

            // Save each CreditStatus in the many-to-many relationship
            for (CreditStatus status : creditRequest.getStatuses()) {
                if (status.getId() == null) {
                    em.persist(status);
                } else {
                    em.merge(status); // Update if it already exists
                }
            }

            em.persist(creditRequest);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Re-throw the exception for higher-level handling
        }
        return creditRequest;
    }

    @Override
    public Optional<CreditRequest> getCreditRequest(Long creditRequestId) {
        return Optional.empty();
    }


    @Override
    public List<CreditRequest> getAllCreditRequests() {
        return getEntityManager().createQuery("SELECT cr FROM CreditRequest cr", CreditRequest.class).getResultList();
    }



    @Override
    public void deleteCreditRequest(Long creditRequestId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CreditRequest creditRequest = em.find(CreditRequest.class, creditRequestId);
            if (creditRequest != null) {
                em.remove(creditRequest);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to delete CreditRequest", e);
        }
    }

    @Override
    public void updateCreditRequest(CreditRequest creditRequest) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Ensure all statuses are updated or persisted before updating the request
            for (CreditStatus status : creditRequest.getStatuses()) {
                if (status.getId() == null) {
                    em.persist(status);
                } else {
                    em.merge(status);
                }
            }

            // Update the CreditRequest
            em.merge(creditRequest);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to update CreditRequest", e);
        }
    }

    @Override
    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        return getEntityManager().createQuery(
                        "SELECT cr FROM CreditRequest cr JOIN cr.statuses s WHERE s = :status", CreditRequest.class)
                .setParameter("status", status)
                .getResultList();
    }
}
