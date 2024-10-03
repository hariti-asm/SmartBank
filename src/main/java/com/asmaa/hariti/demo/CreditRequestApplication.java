package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.dao.implementations.CreditRequestDAOImpl;
import com.asmaa.hariti.demo.helpers.CreditRequestValidator;
import com.asmaa.hariti.demo.helpers.EntityManagerSingleton;
import com.asmaa.hariti.demo.model.entities.CreditRequest;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CreditRequestApplication {

    private static final CreditRequestDAOImpl creditRequestDAO = new CreditRequestDAOImpl();

    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            System.out.println("=== Creating a new Credit Request ===");
            CreditRequest newRequest = createNewCreditRequest();

            List<String> validationErrors = CreditRequestValidator.validate(newRequest);
            if (!validationErrors.isEmpty()) {
                System.out.println("Credit request contains errors:");
                validationErrors.forEach(System.out::println);
                entityManager.getTransaction().rollback();
                return;
            }

            CreditRequest savedRequest = creditRequestDAO.save(newRequest);
            entityManager.getTransaction().commit();

            System.out.println("Credit request submitted successfully!");
            printCreditRequest(Optional.of(savedRequest));

            System.out.println("\n=== Fetching all Credit Requests ===");
            List<CreditRequest> allRequests = creditRequestDAO.getAllCreditRequests();
            for (CreditRequest allRequest : allRequests) {
                printCreditRequest(Optional.ofNullable(allRequest));
            }

            System.out.println("\n=== Fetching Credit Request by ID ===");
            Optional<CreditRequest> fetchedRequest = creditRequestDAO.getCreditRequest(savedRequest.getId());
            if (fetchedRequest.isPresent()) {
                printCreditRequest(fetchedRequest);
            } else {
                System.out.println("Credit request with ID " + savedRequest.getId() + " not found.");
            }

            System.out.println("\n=== Updating the Credit Request ===");
            savedRequest.setAmount(new BigDecimal("30000.00"));
            savedRequest.setInterestRate(new BigDecimal("6.25"));
            creditRequestDAO.updateCreditRequest(savedRequest);

            Optional<CreditRequest> updatedRequest = creditRequestDAO.getCreditRequest(savedRequest.getId());
            System.out.println("Updated Credit Request:");
            printCreditRequest(updatedRequest);

            System.out.println("\n=== Deleting the Credit Request ===");
            creditRequestDAO.deleteCreditRequest(savedRequest.getId());

            Optional<CreditRequest> deletedRequest = creditRequestDAO.getCreditRequest(savedRequest.getId());
            if (deletedRequest.isEmpty()) {
                System.out.println("Credit request with ID " + savedRequest.getId() + " successfully deleted.");
            }

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private static CreditRequest createNewCreditRequest() {
        return new CreditRequest(

        );
    }

    public static void printCreditRequest(Optional<CreditRequest> request) {
        if (request.isPresent()) {
            System.out.println("Request ID: " + request.get().getId());
            System.out.println("Customer Name: " + request.get().getCustomerName());
            System.out.println("Amount: $" + request.get().getAmount());
            System.out.println("Request Date: " + request.get().getRequestDate());
            System.out.println("Term: " + request.get().getTerm() + " months");
            System.out.println("Interest Rate: " + request.get().getInterestRate() + "%");
            System.out.println("Status: " + request.get().getStatus());
            System.out.println("-----------------------------------");
        }
    }
}
