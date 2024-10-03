package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.dao.implementations.CreditRequestDAOImpl;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditRequestApplication {

    private static final CreditRequestDAOImpl creditRequestDAO = new CreditRequestDAOImpl();

    public static void main(String[] args) {
        EntityManagerSingleton.getEntityManager().getTransaction().begin();

        System.out.println("Creating a new Credit Request...");

        CreditRequest newRequest = createNewCreditRequest();
        CreditRequest savedRequest = creditRequestDAO.save(newRequest);

        System.out.println("Credit request submitted successfully!");
        System.out.println("Request ID: " + savedRequest.getId());
        System.out.println("Customer Name: " + savedRequest.getCustomerName());
        System.out.println("Amount: $" + savedRequest.getAmount());
        System.out.println("Request Date: " + savedRequest.getRequestDate());
        System.out.println("Term: " + savedRequest.getTerm() + " months");
        System.out.println("Interest Rate: " + savedRequest.getInterestRate() + "%");
        System.out.println("Status: " + savedRequest.getStatus());
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