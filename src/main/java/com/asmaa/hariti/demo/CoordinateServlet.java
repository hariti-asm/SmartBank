package com.asmaa.hariti.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "coordinateServlet", value = "/coordinates")
public class CoordinateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String project = (String) session.getAttribute("project");
        String job = (String) session.getAttribute("job");
        String amount = (String) session.getAttribute("amount");
        String duration = (String) session.getAttribute("duration");
        String email = (String) session.getAttribute("email");
        String phone = (String) session.getAttribute("phone");

        // Debug logging
        System.out.println("CoordinateServlet doGet - Job from session: " + job);

        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("project", project);
        request.setAttribute("job", job);
        request.setAttribute("amount", amount);
        request.setAttribute("duration", duration);

        request.getRequestDispatcher("/coordinates.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String job = request.getParameter("job");

        if (job == null || job.isEmpty()) {
            job = (String) session.getAttribute("job");
        }

        session.setAttribute("email", email);
        session.setAttribute("phone", phone);
        session.setAttribute("job", job);

        // Debug logging
        System.out.println("CoordinateServlet doPost - Setting job in session: " + job);

        response.sendRedirect(request.getContextPath() + "/personalInfo");
    }
}