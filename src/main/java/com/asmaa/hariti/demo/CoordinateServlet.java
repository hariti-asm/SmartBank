package com.asmaa.hariti.demo;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "coordinateServlet", value = "/coordinates")
public class CoordinateServlet extends HttpServlet {
    private String message;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("</body></html>");
        request.getRequestDispatcher("/coordinates.jsp").forward(request, response);

    }
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     String email = request.getParameter("email");
     String phone = request.getParameter("phone");

 }
    public void destroy() {
    }

}