package com.asmaa.hariti.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "homeServlet", value = "/")
public class HomeServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Welcome to Loan Simulation</h2>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String project = request.getParameter("project");
        String status = request.getParameter("status");
        String amount = request.getParameter("amount");
        String duration = request.getParameter("duration");

        // Process the input data
        out.println("<html><body>");
        out.println("<h2>Your Loan Simulation</h2>");
        out.println("<p>Project: " + project + "</p>");
        out.println("<p>Status: " + status + "</p>");
        out.println("<p>Amount: " + amount + "</p>");
        out.println("<p>Duration: " + duration + " months</p>");
        out.println("</body></html>");
    }
}
