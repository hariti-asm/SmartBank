package com.asmaa.hariti.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "homeServlet", urlPatterns = {"", "/"})
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling GET request in HomeServlet");
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling POST request in HomeServlet");

        HttpSession session = request.getSession();

        try {
            String project = getParameterSafely(request, "project");
            String job = getParameterSafely(request, "job");
            BigDecimal amount = parseBigDecimal(request.getParameter("amount"));
            Integer duration = parseInteger(request.getParameter("duration"));
            BigDecimal monthlyPayment = parseBigDecimal(request.getParameter("monthly"));
            BigDecimal folderCost = parseBigDecimal(request.getParameter("folderCost"));

            session.setAttribute("project", project);
            session.setAttribute("job", job);
            session.setAttribute("amount", amount);
            session.setAttribute("duration", duration);
            session.setAttribute("monthlyPayment", monthlyPayment);
            session.setAttribute("folderCost", folderCost);

            logSessionAttributes(session);

            response.sendRedirect(request.getContextPath() + "/coordinates");
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid input data", e);
            session.setAttribute("errorMessage", "Invalid input data. Please check your entries and try again.");
            response.sendRedirect(request.getContextPath() + "/error");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in HomeServlet", e);
            session.setAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private String getParameterSafely(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        LOGGER.info(String.format("Parameter %s: %s", paramName, value));
        return value != null ? value.trim() : "";
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            LOGGER.info("Received null or empty value for BigDecimal parsing");
            return null;
        }
        try {
            BigDecimal parsed = new BigDecimal(value.trim());
            LOGGER.info(String.format("Parsed BigDecimal value: %s", parsed));
            return parsed;
        } catch (NumberFormatException e) {
            LOGGER.warning(String.format("Invalid BigDecimal format: %s", value));
            throw new IllegalArgumentException("Invalid number format: " + value, e);
        }
    }

    private Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            LOGGER.info("Received null or empty value for Integer parsing");
            return null;
        }
        try {
            Integer parsed = Integer.valueOf(value.trim());
            LOGGER.info(String.format("Parsed Integer value: %s", parsed));
            return parsed;
        } catch (NumberFormatException e) {
            LOGGER.warning(String.format("Invalid Integer format: %s", value));
            throw new IllegalArgumentException("Invalid number format: " + value, e);
        }
    }

    private void logSessionAttributes(HttpSession session) {
        LOGGER.info("Session attributes set:");
        LOGGER.info("project: " + session.getAttribute("project"));
        LOGGER.info("job: " + session.getAttribute("job"));
        LOGGER.info("amount: " + session.getAttribute("amount"));
        LOGGER.info("duration: " + session.getAttribute("duration"));
        LOGGER.info("monthlyPayment: " + session.getAttribute("monthlyPayment"));
        LOGGER.info("folderCost: " + session.getAttribute("folderCost"));
    }
}