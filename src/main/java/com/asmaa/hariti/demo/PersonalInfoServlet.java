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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "personalInfoServlet", urlPatterns = {"/personalInfo"})
public class PersonalInfoServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PersonalInfoServlet.class.getName());

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling GET request in PersonalInfoServlet");
        HttpSession session = request.getSession();

        try {
            String project = (String) session.getAttribute("project");
            String job = (String) session.getAttribute("job");
            BigDecimal amount = (BigDecimal) session.getAttribute("amount");
            Integer duration = (Integer) session.getAttribute("duration");
            String email = (String) session.getAttribute("email");
            String phone = (String) session.getAttribute("phone");
            BigDecimal folderCost = (BigDecimal) session.getAttribute("folderCost");
            String firstName = (String) session.getAttribute("firstName");
            String lastName = (String) session.getAttribute("lastName");

            LOGGER.info("Retrieved from session - Job: " + job + ", Amount: " + amount + ", Duration: " + duration);

            request.setAttribute("project", project);
            request.setAttribute("job", job);
            request.setAttribute("amount", amount);
            request.setAttribute("duration", duration);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("folderCost", folderCost);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);

            request.getRequestDispatcher("/WEB-INF/views/personalInfo.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in PersonalInfoServlet doGet", e);
            session.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling POST request in PersonalInfoServlet");
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

            BigDecimal amount = (BigDecimal) session.getAttribute("amount");
            if (amount != null) {
                creditRequest.setAmount(amount);
            }

            Integer duration = (Integer) session.getAttribute("duration");
            if (duration != null) {
                creditRequest.setDuration(duration);
            }

            String monthlyPayment = request.getParameter("monthlyPayment");
            if (monthlyPayment != null && !monthlyPayment.isEmpty()) {
                creditRequest.setMonthlyPayment(new BigDecimal(monthlyPayment));
            }

            BigDecimal folderCost = (BigDecimal) session.getAttribute("folderCost");
            if (folderCost != null) {
                creditRequest.setFolderCost(folderCost);
            }

            creditRequest.setRequestDate(LocalDate.now());

            validationErrors.addAll(CreditRequestValidator.validate(creditRequest));

            if (!validationErrors.isEmpty()) {
                LOGGER.info("Validation errors: " + validationErrors);
                request.setAttribute("validationErrors", validationErrors);
                setRequestAttributes(request, creditRequest);
                request.getRequestDispatcher("/WEB-INF/views/personalInfo.jsp").forward(request, response);
                return;
            }

            LOGGER.info("Creating credit request");
            creditRequestService.createCreditRequest(creditRequest);
            LOGGER.info("Credit request created successfully");
            request.setAttribute("message", "Credit request submitted successfully!");
            request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in PersonalInfoServlet doPost", e);
            validationErrors.add("An error occurred while processing your request: " + e.getMessage());
            request.setAttribute("validationErrors", validationErrors);
            setRequestAttributes(request, new CreditRequest());
            request.getRequestDispatcher("/WEB-INF/views/personalInfo.jsp").forward(request, response);
        }
    }

    private void setRequestAttributes(HttpServletRequest request, CreditRequest creditRequest) {
        request.setAttribute("firstName", creditRequest.getFirstName());
        request.setAttribute("lastName", creditRequest.getLastName());
        request.setAttribute("cin", creditRequest.getCin());
        request.setAttribute("email", creditRequest.getEmail());
        request.setAttribute("phone", creditRequest.getPhone());
        request.setAttribute("job", creditRequest.getJob());
        request.setAttribute("amount", creditRequest.getAmount());
        request.setAttribute("duration", creditRequest.getDuration());
        request.setAttribute("birthdate", creditRequest.getBirthdate());
        request.setAttribute("workdate", creditRequest.getWorkDate());
        request.setAttribute("monthlyPayment", creditRequest.getMonthlyPayment());
        request.setAttribute("revenues", creditRequest.getRevenues());
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
}