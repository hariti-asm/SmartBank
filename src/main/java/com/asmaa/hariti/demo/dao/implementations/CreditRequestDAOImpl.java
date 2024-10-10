package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditRequestStatusHistory;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreditRequestDAOImpl implements CreditRequestDAO {
    private EntityManager getEntityManager() {
        return EntityManagerSingleton.getEntityManager();
    }

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            if (creditRequest.getStatusHistory() != null && !creditRequest.getStatusHistory().isEmpty()) {
                for (CreditRequestStatusHistory statusHistory : creditRequest.getStatusHistory()) {
                    CreditStatus status = statusHistory.getStatus();

                    CreditStatus newStatus = new CreditStatus();
                    newStatus.setName(status.getName());
                    newStatus.setDescription(status.getDescription());
                    em.persist(newStatus);

                    statusHistory.setStatus(newStatus);
                }
            }

            if (creditRequest.getId() == null) {
                em.persist(creditRequest);
            } else {
                creditRequest = em.merge(creditRequest);
            }

            for (CreditRequestStatusHistory statusHistory : creditRequest.getStatusHistory()) {
                if (statusHistory.getId() == null) {
                    statusHistory.setCreditRequest(creditRequest);
                    em.persist(statusHistory);
                } else {
                    em.merge(statusHistory);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save CreditRequest", e);
        }
        return creditRequest;
    }
    @Override
    public Optional<CreditRequest> getCreditRequest(Long creditRequestId) {
        EntityManager em = getEntityManager();
        CreditRequest creditRequest = em.find(CreditRequest.class, creditRequestId);
        return Optional.ofNullable(creditRequest);
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
                        "SELECT DISTINCT cr FROM CreditRequest cr JOIN cr.statusHistory sh WHERE sh.status = :status",
                        CreditRequest.class)
                .setParameter("status", status)
                .getResultList();
    }
    public void addStatusToCreditRequest(Long creditRequestId, CreditStatus newStatus) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CreditRequest creditRequest = em.find(CreditRequest.class, creditRequestId);
            if (creditRequest != null) {
                CreditRequestStatusHistory newStatusHistory = new CreditRequestStatusHistory(creditRequest, newStatus, LocalDateTime.now());
                creditRequest.getStatusHistory().add(newStatusHistory);
                em.persist(newStatusHistory);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to add status to CreditRequest", e);
        }
    }

}
