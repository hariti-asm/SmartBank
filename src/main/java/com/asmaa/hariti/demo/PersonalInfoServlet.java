package com.asmaa.hariti.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "personalInfoServlet", value = "/personalInfo")
public class PersonalInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("</body></html>");
        request.getRequestDispatcher("/personnal_info.jsp").forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        String civilite = request.getParameter("civilite");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cin = request.getParameter("cin");
        String dateNaissance = request.getParameter("date-naissance");
        String dateEmbauche = request.getParameter("date-embauche");
        String revenus = request.getParameter("revenus");
        String creditsEnCours = request.getParameter("credits-en-cours");
        String conditions = request.getParameter("conditions");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Données reçues</h1>");
        out.println("<p>Civilité: " + civilite + "</p>");
        out.println("<p>Nom: " + nom + "</p>");
        out.println("<p>Prénom: " + prenom + "</p>");
        out.println("<p>Numéro CIN: " + cin + "</p>");
        out.println("<p>Date de naissance: " + dateNaissance + "</p>");
        out.println("<p>Date d'embauche: " + dateEmbauche + "</p>");
        out.println("<p>Revenus: " + revenus + " DH</p>");
        out.println("<p>Crédits en cours: " + creditsEnCours + "</p>");
        out.println("<p>Conditions acceptées: " + (conditions != null ? "Oui" : "Non") + "</p>");
        out.println("</body></html>");
    }
}
