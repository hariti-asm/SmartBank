package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.dao.repositories.CreditRequestDAO;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String[] attributes = {"project", "status", "amount", "duration", "email", "phone"};
        for (String attribute : attributes) {
            request.setAttribute(attribute, session.getAttribute(attribute));
        }

        request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String cin = request.getParameter("cin");

        if (isNullOrEmpty(firstName) || isNullOrEmpty(lastName) || isNullOrEmpty(cin)) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
            return;
        }

        LocalDate birthdate = parseLocalDate(request.getParameter("birthdate"));
        LocalDate workDate = parseLocalDate(request.getParameter("workdate"));
        BigDecimal revenues = parseBigDecimal(request.getParameter("revenus"));

        CreditRequest creditRequest = new CreditRequest(

        );

        creditRequestService.createCreditRequest(creditRequest);

        request.setAttribute("message", "Credit request submitted successfully!");
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private LocalDate parseLocalDate(String dateStr) {
        return isNullOrEmpty(dateStr) ? null : LocalDate.parse(dateStr);
    }

    private BigDecimal parseBigDecimal(String numStr) {
        return isNullOrEmpty(numStr) ? null : new BigDecimal(numStr);
    }
}