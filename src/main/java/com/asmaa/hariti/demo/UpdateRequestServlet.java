package com.asmaa.hariti.demo;

import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditRequestStatusHistory;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import com.asmaa.hariti.demo.service.CreditRequestService;
import jakarta.inject.Inject;
import jakarta.json.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "updateRequestServlet", urlPatterns = {"/api/creditRequests/*"})
public class UpdateRequestServlet extends HttpServlet {
    private CreditRequestService creditRequestService;

    public UpdateRequestServlet() {}

    @Inject
    public UpdateRequestServlet(CreditRequestService creditRequestService) {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Received GET request: " + request.getRequestURI());
        String pathInfo = request.getPathInfo();
        System.out.println("Path info: " + pathInfo);
        if (pathInfo != null && pathInfo.matches("/\\d+/statushistory")) {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            getStatusHistory(id, response);
        } else {
            System.out.println("Invalid URL for GET request: " + pathInfo);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL for GET request: " + pathInfo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.matches("/\\d+/status")) {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            try (JsonReader jsonReader = Json.createReader(request.getReader())) {
                JsonObject jsonObject = jsonReader.readObject();
                String statusName = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                CreditStatus newStatus = new CreditStatus(statusName, description);
                addStatus(id, newStatus, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL for POST request");
        }
    }

    private void getStatusHistory(long id, HttpServletResponse response) throws IOException {
        System.out.println("Fetching CreditRequest with id: " + id);
        try {
            Optional<CreditRequest> optionalCreditRequest = creditRequestService.getCreditRequest(id);
            if (optionalCreditRequest.isPresent()) {
                CreditRequest creditRequest = optionalCreditRequest.get();
                System.out.println("CreditRequest found: " + creditRequest);

                List<CreditRequestStatusHistory> statusHistory = creditRequest.getStatusHistory();
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

                for (CreditRequestStatusHistory history : statusHistory) {
                    JsonObjectBuilder historyObjectBuilder = Json.createObjectBuilder()
                            .add("status", history.getStatus().getName())
                            .add("updatedAt", history.getUpdatedAt().toString());
                    jsonArrayBuilder.add(historyObjectBuilder);
                }

                JsonArray jsonArray = jsonArrayBuilder.build();
                sendJsonResponse(response, HttpServletResponse.SC_OK, Json.createObjectBuilder()
                        .add("statusHistory", jsonArray)
                        .build());
            } else {
                System.out.println("CreditRequest not found for id: " + id);
                sendJsonResponse(response, HttpServletResponse.SC_NOT_FOUND, Json.createObjectBuilder()
                        .add("error", "Credit request not found")
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error fetching CreditRequest: " + e.getMessage());
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Json.createObjectBuilder()
                    .add("error", "Error fetching status history: " + e.getMessage())
                    .build());
        }
    }
    private void addStatus(long id, CreditStatus newStatus, HttpServletResponse response) throws IOException {
        try {
            Optional<CreditRequest> optionalCreditRequest = creditRequestService.getCreditRequest(id);
            if (optionalCreditRequest.isPresent()) {
                CreditRequest creditRequest = optionalCreditRequest.get();
                if (creditRequest.getStatusHistory().isEmpty()) {
                    CreditRequestStatusHistory initialStatus = new CreditRequestStatusHistory();
                    initialStatus.setStatus(newStatus);
                    initialStatus.setUpdatedAt(LocalDateTime.now());
                    creditRequest.addStatusHistory(initialStatus.getStatus());
                } else {
                    creditRequest.addStatusHistory(newStatus);
                }
                creditRequestService.updateCreditRequest(creditRequest);

                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Status updated successfully")
                        .add("newStatus", newStatus.getName())
                        .build();

                sendJsonResponse(response, HttpServletResponse.SC_OK, jsonResponse);
            } else {
                sendJsonResponse(response, HttpServletResponse.SC_NOT_FOUND, Json.createObjectBuilder()
                        .add("error", "Credit request not found")
                        .build());
            }
        } catch (Exception e) {
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Json.createObjectBuilder()
                    .add("error", "Error updating status: " + e.getMessage())
                    .build());
        }
    }

    private void sendJsonResponse(HttpServletResponse response, int status, JsonObject jsonObject) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        try (JsonWriter jsonWriter = Json.createWriter(response.getWriter())) {
            jsonWriter.writeObject(jsonObject);
        }
    }
}