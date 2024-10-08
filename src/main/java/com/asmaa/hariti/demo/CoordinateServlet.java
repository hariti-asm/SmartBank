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

@WebServlet(name = "coordinateServlet", urlPatterns = {"/coordinates"})
public class CoordinateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CoordinateServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling GET request in CoordinateServlet");
        HttpSession session = request.getSession();

        try {
            String project = (String) session.getAttribute("project");
            String job = (String) session.getAttribute("job");
            BigDecimal amount = (BigDecimal) session.getAttribute("amount");
            Integer duration = (Integer) session.getAttribute("duration");
            String email = (String) session.getAttribute("email");
            String phone = (String) session.getAttribute("phone");

            LOGGER.info("Retrieved from session - Job: " + job + ", Amount: " + amount + ", Duration: " + duration);

            request.setAttribute("project", project);
            request.setAttribute("job", job);
            request.setAttribute("amount", amount != null ? amount.toString() : null);
            request.setAttribute("duration", duration != null ? duration.toString() : null);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);

            request.getRequestDispatcher("/WEB-INF/views/coordinates.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in CoordinateServlet doGet", e);
            session.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Handling POST request in CoordinateServlet");
        HttpSession session = request.getSession();

        try {
            String email = getParameterSafely(request, "email");
            String phone = getParameterSafely(request, "phone");
            String job = getParameterSafely(request, "job");

            if (job.isEmpty()) {
                job = (String) session.getAttribute("job");
                LOGGER.info("Job not provided in request, using from session: " + job);
            }

            session.setAttribute("email", email);
            session.setAttribute("phone", phone);
            session.setAttribute("job", job);

            LOGGER.info("Set in session - Email: " + email + ", Phone: " + phone + ", Job: " + job);

            response.sendRedirect(request.getContextPath() + "/personalInfo");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in CoordinateServlet doPost", e);
            session.setAttribute("errorMessage", "An error occurred while processing your request. Please try again.");
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private String getParameterSafely(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        LOGGER.info(String.format("Parameter %s: %s", paramName, value));
        return value != null ? value.trim() : "";
    }
}