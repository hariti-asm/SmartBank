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
        // Retrieve the loan data from the session to display in the form
        HttpSession session = request.getSession();

        String project = (String) session.getAttribute("project");
        String status = (String) session.getAttribute("status");
        String amount = (String) session.getAttribute("amount");
        String duration = (String) session.getAttribute("duration");

        // Pass the loan data to the JSP page
        request.setAttribute("project", project);
        request.setAttribute("status", status);
        request.setAttribute("amount", amount);
        request.setAttribute("duration", duration);

        // Forward to the JSP page for entering coordinates
        request.getRequestDispatcher("/coordinates.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Collect coordinates data
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Store coordinates data in session
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);

        // Redirect to personal info page
        response.sendRedirect(request.getContextPath() + "/personalInfo");
    }
}
