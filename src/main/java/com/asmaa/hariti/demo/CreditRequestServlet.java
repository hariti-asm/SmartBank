package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.service.CreditRequestService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "creditRequestServlet", value = "/requests")
public class CreditRequestServlet extends HttpServlet {

    @Inject
    private CreditRequestService creditRequestService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CreditRequest> creditRequestList = creditRequestService.getAllCreditRequests();
        request.setAttribute("creditRequestList", creditRequestList);
        request.getRequestDispatcher("/WEB-INF/views/creditRequests.jsp").forward(request, response);
    }
}