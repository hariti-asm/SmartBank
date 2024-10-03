package com.asmaa.hariti.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "personalInfoServlet", value = "/personalInfo")
public class PersonalInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve all data from session
        HttpSession session = request.getSession();

        String project = (String) session.getAttribute("project");
        String status = (String) session.getAttribute("status");
        String amount = (String) session.getAttribute("amount");
        String duration = (String) session.getAttribute("duration");

        String email = (String) session.getAttribute("email");
        String phone = (String) session.getAttribute("phone");

        // Pass the data to the JSP page for display
        request.setAttribute("project", project);
        request.setAttribute("status", status);
        request.setAttribute("amount", amount);
        request.setAttribute("duration", duration);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);

        // Forward to personalInfo.jsp
        request.getRequestDispatcher("/personalInfo.jsp").forward(request, response);
    }
}
