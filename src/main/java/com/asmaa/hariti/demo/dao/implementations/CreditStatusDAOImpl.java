package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditStatusDAO;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CreditStatusDAOImpl implements CreditStatusDAO {

    @PersistenceContext
    private EntityManager em;

    public CreditStatusDAOImpl() {
        this.em = EntityManagerSingleton.getEntityManager();
    }

    @Override
    public CreditStatus save(CreditStatus creditStatus) {
        try {
            em.getTransaction().begin();
            em.persist(creditStatus);
            em.getTransaction().commit();
            return creditStatus;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CreditStatus> getCreditStatus(String creditStatusId) {
        CreditStatus creditStatus = em.find(CreditStatus.class, creditStatusId);
        return Optional.ofNullable(creditStatus);
    }

    @Override
    public List<CreditStatus> getAllCreditRequests() {
        return em.createQuery("SELECT cs FROM CreditStatus cs", CreditStatus.class).getResultList();
    }

    @Override
    public void deleteCreditStatus(String creditStatusId) {
        try {
            em.getTransaction().begin();
            CreditStatus creditStatus = em.find(CreditStatus.class, creditStatusId);
            if (creditStatus != null) {
                em.remove(creditStatus);
            } else {
                System.out.println("CreditStatus not found for ID: " + creditStatusId);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateCreditStatus(CreditStatus creditStatus) {
        if (creditStatus == null) {
            throw new IllegalArgumentException("CreditStatus cannot be null");
        }

        try {
            em.getTransaction().begin();
            em.merge(creditStatus);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
