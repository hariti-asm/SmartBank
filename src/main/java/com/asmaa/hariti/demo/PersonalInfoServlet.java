package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.service.CreditRequestService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "personalInfoServlet", value = "/personalInfo")
public class PersonalInfoServlet extends HttpServlet {

    private CreditRequestService creditRequestService;

    public PersonalInfoServlet() {
    }

    @Inject
    public PersonalInfoServlet(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        if (creditRequestService == null) {
            creditRequestService = new CreditRequestService();
        }
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String project = (String) session.getAttribute("project");
        String job = (String) session.getAttribute("job");
        String amount = (String) session.getAttribute("amount");
        String duration = (String) session.getAttribute("duration");
        String email = (String) session.getAttribute("email");
        String phone = (String) session.getAttribute("phone");
        Double folderCost = (Double) session.getAttribute("folderCost");
        String firstName = (String) session.getAttribute("firstName");
        String lastName = (String) session.getAttribute("lastName");
        System.out.println("PersonalInfoServlet doGet - Job from session: " + job);

        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("project", project);
        request.setAttribute("job", job);
        request.setAttribute("amount", amount);
        request.setAttribute("duration", duration);
        request.setAttribute("folderCost", folderCost);

        request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost method called");

        HttpSession session = request.getSession();

        try {
            CreditRequest creditRequest = new CreditRequest();
            creditRequest.setFirstName(getRequiredParameter(request, "firstName"));
            creditRequest.setLastName(getRequiredParameter(request, "lastName"));
            creditRequest.setCin(getRequiredParameter(request, "cin"));

            String email = (String) session.getAttribute("email");
            String phone = (String) session.getAttribute("phone");
            String job = (String) session.getAttribute("job");
            String amount = (String) session.getAttribute("amount");
            String duration = (String) session.getAttribute("duration");
            if (email != null && !email.isEmpty()) {
                creditRequest.setEmail(email);
            } else {
                creditRequest.setEmail(getRequiredParameter(request, "email"));
            }

            if (phone != null && !phone.isEmpty()) {
                creditRequest.setPhone(phone);
            } else {
                creditRequest.setPhone(getRequiredParameter(request, "phone"));
            }

            if (job != null && !job.isEmpty()) {
                creditRequest.setJob(job);
            } else {
                creditRequest.setJob(getRequiredParameter(request, "job"));
            }

            if (amount != null && !amount.isEmpty()) {
                creditRequest.setRevenues(new BigDecimal(amount));
            } else {
                creditRequest.setRevenues(parseBigDecimal(getRequiredParameter(request, "amount")));
            }

            if (duration != null && !duration.isEmpty()) {
                creditRequest.setDuration(Integer.parseInt(duration));
            } else {
                creditRequest.setDuration(Integer.parseInt(getRequiredParameter(request, "duration")));
            }

            String monthlyPayment = request.getParameter("monthlyPayment");
            if (monthlyPayment != null && !monthlyPayment.isEmpty()) {
                creditRequest.setMonthlyPayment(new BigDecimal(monthlyPayment));
            } else {

                creditRequest.setMonthlyPayment(null);
            }

            String folderCost = request.getParameter("folderCost");
            if (folderCost == null || folderCost.isEmpty()) {
                System.out.println("Folder cost is null or empty");
                creditRequest.setFolderCost(null);
            }

            System.out.println("Email: " + creditRequest.getEmail());
            System.out.println("Phone: " + creditRequest.getPhone());
            System.out.println("Job: " + creditRequest.getJob());
            System.out.println("Amount: " + creditRequest.getRevenues());
            System.out.println("Duration: " + creditRequest.getDuration());
            System.out.println("Monthly Payment: " + creditRequest.getMonthlyPayment());
            System.out.println("Folder Cost: " + creditRequest.getFolderCost());

            creditRequest.setBirthdate(parseLocalDate(getRequiredParameter(request, "birthdate")));
            creditRequest.setWorkDate(parseLocalDate(getRequiredParameter(request, "workdate")));
            creditRequest.setRequestDate(LocalDate.now());

            // Log the credit request object
            System.out.println("Calling creditRequestService.createCreditRequest hamza");
            creditRequestService.createCreditRequest(creditRequest);
            System.out.println("CreditRequest created successfully");
            request.setAttribute("message", "Credit request submitted successfully!");
            System.out.println("Forwarding to success.jsp");
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException caught: " + e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request. Please try again.");
            request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
        }
    }
    private String getRequiredParameter(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        if (isNullOrEmpty(value)) {
            throw new IllegalArgumentException(paramName + " is required.");
        }
        return value;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private LocalDate parseLocalDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for: " + dateStr);
        }
    }

    private BigDecimal parseBigDecimal(String numStr) {
        try {
            return new BigDecimal(numStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for: " + numStr);
        }
    }
}