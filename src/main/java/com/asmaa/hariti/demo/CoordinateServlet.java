package com.asmaa.hariti.demo;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "coordinateServlet", value = "/coordinates")
public class CoordinateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String project = (String) session.getAttribute("project");
        String status = (String) session.getAttribute("status");
        String amount = (String) session.getAttribute("amount");
        String duration = (String) session.getAttribute("duration");

        request.setAttribute("project", project);
        request.setAttribute("status", status);
        request.setAttribute("amount", amount);
        request.setAttribute("duration", duration);

        request.getRequestDispatcher("/coordinates.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);

        response.sendRedirect(request.getContextPath() + "/personalInfo");
    }
}