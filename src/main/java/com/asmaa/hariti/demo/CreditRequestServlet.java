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

public class CreditRequestServlet  extends HttpServlet {
    private CreditRequestService creditRequestService;
public CreditRequestServlet() {}
    @Inject
    public CreditRequestServlet(CreditRequestService creditRequestService) {
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
        List<CreditRequest> creditRequestList = creditRequestService.getAllCreditRequests();
        request.setAttribute("creditRequestList", creditRequestList);
        request.getRequestDispatcher("/WEB-INF/views/creditRequests.jsp").forward(request, response);

    }
}
