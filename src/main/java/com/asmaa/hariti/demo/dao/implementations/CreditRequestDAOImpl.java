package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditRequestStatusHistory;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreditRequestDAOImpl implements CreditRequestDAO {
    @Inject
    private EntityManager entityManager;

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (creditRequest.getStatusHistory() != null && !creditRequest.getStatusHistory().isEmpty()) {
                for (CreditRequestStatusHistory statusHistory : creditRequest.getStatusHistory()) {
                    CreditStatus status = statusHistory.getStatus();

                    CreditStatus newStatus = new CreditStatus();
                    newStatus.setName(status.getName());
                    entityManager.persist(newStatus);

                    statusHistory.setStatus(newStatus);
                    statusHistory.setDescription("Status changed to " + status.getName());
                }
            }

            if (creditRequest.getId() == null) {
                entityManager.persist(creditRequest);
            } else {
                creditRequest = entityManager.merge(creditRequest);
            }

            for (CreditRequestStatusHistory statusHistory : creditRequest.getStatusHistory()) {
                if (statusHistory.getId() == null) {
                    statusHistory.setCreditRequest(creditRequest);
                    entityManager.persist(statusHistory);
                } else {
                    entityManager.merge(statusHistory);
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
        CreditRequest creditRequest = entityManager.find(CreditRequest.class, creditRequestId);
        return Optional.ofNullable(creditRequest);
    }

    @Override
    public List<CreditRequest> getAllCreditRequests() {
        return entityManager.createQuery("SELECT cr FROM CreditRequest cr", CreditRequest.class).getResultList();
    }

    @Override
    public void deleteCreditRequest(Long creditRequestId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            CreditRequest creditRequest = entityManager.find(CreditRequest.class, creditRequestId);
            if (creditRequest != null) {
                entityManager.remove(creditRequest);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete CreditRequest", e);
        }
    }

    @Override
    public void updateCreditRequest(CreditRequest creditRequest) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(creditRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update CreditRequest", e);
        }
    }

    @Override
    public List<CreditRequest> getCreditRequestsByStatus(CreditStatus status) {
        return entityManager.createQuery(
                        "SELECT DISTINCT cr FROM CreditRequest cr JOIN cr.statusHistory sh WHERE sh.status = :status",
                        CreditRequest.class)
                .setParameter("status", status)
                .getResultList();
    }

    public void addStatusToCreditRequest(Long creditRequestId, CreditStatus newStatus) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            CreditRequest creditRequest = entityManager.find(CreditRequest.class, creditRequestId);
            if (creditRequest != null) {
                CreditRequestStatusHistory newStatusHistory = new CreditRequestStatusHistory(creditRequest, newStatus, LocalDateTime.now());
                creditRequest.getStatusHistory().add(newStatusHistory);
                entityManager.persist(newStatusHistory);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to add status to CreditRequest", e);
        }
    }
}