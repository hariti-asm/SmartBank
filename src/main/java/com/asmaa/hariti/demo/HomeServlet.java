package com.asmaa.hariti.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "homeServlet", value = "/")
public class HomeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String project = request.getParameter("project");
        String status = request.getParameter("status");
        String amount = request.getParameter("amount");
        String duration = request.getParameter("duration");

        HttpSession session = request.getSession();
        session.setAttribute("project", project);
        session.setAttribute("status", status);
        session.setAttribute("amount", amount);
        session.setAttribute("duration", duration);

        response.sendRedirect(request.getContextPath() + "/coordinates");
    }
}