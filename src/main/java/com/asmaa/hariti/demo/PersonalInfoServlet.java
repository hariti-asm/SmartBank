package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.helpers.CreditRequestValidator;
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
import java.util.ArrayList;
import java.util.List;

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
        List<String> validationErrors = new ArrayList<>();

        try {
            CreditRequest creditRequest = new CreditRequest();

            creditRequest.setFirstName(getRequiredParameter(request, "firstName"));
            creditRequest.setLastName(getRequiredParameter(request, "lastName"));
            creditRequest.setCin(getRequiredParameter(request, "cin"));

            try {
                creditRequest.setBirthdate(parseLocalDate(getRequiredParameter(request, "birthdate")));
            } catch (IllegalArgumentException e) {
                validationErrors.add("Invalid birth date format. Please use YYYY-MM-DD.");
            }

            try {
                creditRequest.setWorkDate(parseLocalDate(getRequiredParameter(request, "workdate")));
            } catch (IllegalArgumentException e) {
                validationErrors.add("Invalid work date format. Please use YYYY-MM-DD.");
            }

            creditRequest.setEmail((String) session.getAttribute("email"));
            creditRequest.setPhone((String) session.getAttribute("phone"));
            creditRequest.setJob((String) session.getAttribute("job"));

            String amount = (String) session.getAttribute("amount");
            if (amount != null && !amount.isEmpty()) {
                creditRequest.setRevenues(new BigDecimal(amount));
            }

            String duration = (String) session.getAttribute("duration");
            if (duration != null && !duration.isEmpty()) {
                creditRequest.setDuration(Integer.parseInt(duration));
            }

            String monthlyPayment = request.getParameter("monthlyPayment");
            if (monthlyPayment != null && !monthlyPayment.isEmpty()) {
                creditRequest.setMonthlyPayment(new BigDecimal(monthlyPayment));
            }

            String folderCost = (String) session.getAttribute("folderCost");
            if (folderCost != null && !folderCost.isEmpty()) {
                creditRequest.setFolderCost(new BigDecimal(folderCost));
            }

            creditRequest.setRequestDate(LocalDate.now());

            // Validate the credit request
            validationErrors.addAll(CreditRequestValidator.validate(creditRequest));

            if (!validationErrors.isEmpty()) {
                request.setAttribute("validationErrors", validationErrors);
                setRequestAttributes(request, creditRequest);
                request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
                return;
            }

            System.out.println("Calling creditRequestService.createCreditRequest");
            creditRequestService.createCreditRequest(creditRequest);
            System.out.println("CreditRequest created successfully");
            request.setAttribute("message", "Credit request submitted successfully!");
            request.getRequestDispatcher("/success.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
            validationErrors.add("An error occurred while processing your request: " + e.getMessage());
            request.setAttribute("validationErrors", validationErrors);
            setRequestAttributes(request, new CreditRequest());
            request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
        }
    }

    private void setRequestAttributes(HttpServletRequest request, CreditRequest creditRequest) {
        request.setAttribute("firstName", creditRequest.getFirstName());
        request.setAttribute("lastName", creditRequest.getLastName());
        request.setAttribute("cin", creditRequest.getCin());
        request.setAttribute("email", creditRequest.getEmail());
        request.setAttribute("phone", creditRequest.getPhone());
        request.setAttribute("job", creditRequest.getJob());
        request.setAttribute("amount", creditRequest.getRevenues());
        request.setAttribute("duration", creditRequest.getDuration());
        request.setAttribute("birthdate", creditRequest.getBirthdate());
        request.setAttribute("workdate", creditRequest.getWorkDate());
        request.setAttribute("monthlyPayment", creditRequest.getMonthlyPayment());
        request.setAttribute("folderCost", creditRequest.getFolderCost());
    }

    private String getRequiredParameter(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing required parameter: " + paramName);
        }
        return value.trim();
    }


    private LocalDate parseLocalDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
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