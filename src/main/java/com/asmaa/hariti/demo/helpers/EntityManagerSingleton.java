package com.asmaa.hariti.demo.helpers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerSingleton {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private EntityManagerSingleton() {
    }

    public static EntityManager getEntityManager() {
        if (em == null) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("practice");
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}