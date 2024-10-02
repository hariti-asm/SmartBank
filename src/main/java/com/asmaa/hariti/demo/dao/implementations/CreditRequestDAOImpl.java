package com.asmaa.hariti.demo.dao.implementations;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CreditRequestDAOImpl implements CreditRequestDAO {
    @PersistenceContext
    private EntityManager em;

    public CreditRequestDAOImpl() {
        this.em = EntityManagerSingleton.getEntityManager();
    }

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        try{em.getTransaction().begin();
            em.persist(creditRequest);
            em.getTransaction().commit();
            return creditRequest;} catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
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
        try{
            em.getTransaction().begin();
            CreditRequest creditRequest = getCreditRequest(creditRequestId);
            if(creditRequest != null) {
                em.remove(creditRequest);
            }
            em.getTransaction().commit();
        } catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void updateCreditRequest(CreditRequest creditRequest) {
        try {
            em.getTransaction().begin();
            em.merge(creditRequest);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
