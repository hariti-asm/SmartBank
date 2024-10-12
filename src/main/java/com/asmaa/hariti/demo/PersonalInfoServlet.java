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

    @Inject
    private CreditRequestService creditRequestService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling GET request in PersonalInfoServlet");
        HttpSession session = request.getSession();

        try {
            setRequestAttributesFromSession(request, session);
            request.getRequestDispatcher("/WEB-INF/views/personalInfo.jsp").forward(request, response);
        } catch (Exception e) {
            handleError(e, session, response, request);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling POST request in PersonalInfoServlet");
        HttpSession session = request.getSession();
        List<String> validationErrors = new ArrayList<>();

        try {
            CreditRequest creditRequest = createCreditRequestFromRequest(request, session);
            validationErrors.addAll(CreditRequestValidator.validate(creditRequest));

            if (!validationErrors.isEmpty()) {
                handleValidationErrors(request, response, validationErrors, creditRequest);
                return;
            }

            LOGGER.info("Creating credit request");
            creditRequestService.createCreditRequest(creditRequest);
            LOGGER.info("Credit request created successfully");
            request.setAttribute("message", "Credit request submitted successfully!");
            request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);

        } catch (Exception e) {
            handleError(e, request, response);
        }
    }

    private void setRequestAttributesFromSession(HttpServletRequest request, HttpSession session) {
        String[] attributes = {"project", "job", "amount", "duration", "email", "phone", "folderCost", "firstName", "lastName"};
        for (String attr : attributes) {
            request.setAttribute(attr, session.getAttribute(attr));
        }
    }

    private CreditRequest createCreditRequestFromRequest(HttpServletRequest request, HttpSession session) {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setFirstName(getRequiredParameter(request, "firstName"));
        creditRequest.setLastName(getRequiredParameter(request, "lastName"));
        creditRequest.setCin(getRequiredParameter(request, "cin"));
        creditRequest.setBirthdate(parseLocalDate(getRequiredParameter(request, "birthdate")));
        creditRequest.setWorkDate(parseLocalDate(getRequiredParameter(request, "workdate")));
        creditRequest.setEmail((String) session.getAttribute("email"));
        creditRequest.setPhone((String) session.getAttribute("phone"));
        creditRequest.setJob((String) session.getAttribute("job"));
        creditRequest.setAmount((BigDecimal) session.getAttribute("amount"));
        creditRequest.setDuration((Integer) session.getAttribute("duration"));
        creditRequest.setMonthlyPayment(parseBigDecimal(request.getParameter("monthlyPayment")));
        creditRequest.setFolderCost((BigDecimal) session.getAttribute("folderCost"));
        creditRequest.setRequestDate(LocalDate.now());
        return creditRequest;
    }

    private void handleValidationErrors(HttpServletRequest request, HttpServletResponse response, List<String> validationErrors, CreditRequest creditRequest) throws ServletException, IOException {
        LOGGER.info("Validation errors: " + validationErrors);
        request.setAttribute("validationErrors", validationErrors);
        setRequestAttributes(request, creditRequest);
        request.getRequestDispatcher("/WEB-INF/views/personalInfo.jsp").forward(request, response);
    }

    private void handleError(Exception e, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        LOGGER.log(Level.SEVERE, "Error in PersonalInfoServlet", e);
        session.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
        response.sendRedirect(request.getContextPath() + "/error");
    }

    private void handleError(Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, "Error in PersonalInfoServlet doPost", e);
        List<String> validationErrors = new ArrayList<>();
        validationErrors.add("An error occurred while processing your request: " + e.getMessage());
        request.setAttribute("validationErrors", validationErrors);
        setRequestAttributes(request, new CreditRequest());
        request.getRequestDispatcher("/WEB-INF/views/personalInfo.jsp").forward(request, response);
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

    private BigDecimal parseBigDecimal(String value) {
        return value != null && !value.isEmpty() ? new BigDecimal(value) : null;
    }
}