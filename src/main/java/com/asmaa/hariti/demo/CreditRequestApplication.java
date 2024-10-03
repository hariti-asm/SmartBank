package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.dao.implementations.CreditRequestDAOImpl;
import com.asmaa.hariti.demo.helpers.CreditRequestValidator;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreditRequestApplication {

    private static final CreditRequestDAOImpl creditRequestDAO = new CreditRequestDAOImpl();

    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Creating a new Credit Request...");

        CreditRequest newRequest = createNewCreditRequest();

        List<String> validationErrors = CreditRequestValidator.validate(newRequest);

        if (!validationErrors.isEmpty()) {
            System.out.println("Credit request contains errors:");
            validationErrors.forEach(System.out::println);
            entityManager.getTransaction().rollback();
            entityManager.close();
            return;
        }

        CreditRequest savedRequest = creditRequestDAO.save(newRequest);
        entityManager.getTransaction().commit();

        System.out.println("Credit request submitted successfully!");
        System.out.println("Request ID: " + savedRequest.getId());
        System.out.println("Customer Name: " + savedRequest.getCustomerName());
        System.out.println("Amount: $" + savedRequest.getAmount());
        System.out.println("Request Date: " + savedRequest.getRequestDate());
        System.out.println("Term: " + savedRequest.getTerm() + " months");
        System.out.println("Interest Rate: " + savedRequest.getInterestRate() + "%");
        System.out.println("Status: " + savedRequest.getStatus());

        entityManager.close();
    }

    private static CreditRequest createNewCreditRequest() {
        return new CreditRequest(
                "John Doe",
                new BigDecimal("25000.00"),
                LocalDate.now(),
                36,  // 3-year term
                new BigDecimal("5.75")
        );
    }
}
