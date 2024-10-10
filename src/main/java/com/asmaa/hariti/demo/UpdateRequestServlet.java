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
import java.io.PrintWriter;
import java.io.StringWriter;
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
        String pathInfo = request.getPathInfo();
        System.out.println("Received GET request: " + request.getRequestURI());
        System.out.println("Path info: " + pathInfo);

        if (pathInfo != null && pathInfo.matches("/\\d+/statushistory")) {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            getStatusHistory(id, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL for GET request: " + pathInfo);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        System.out.println("Received POST request: " + request.getRequestURI());
        System.out.println("Path info: " + pathInfo);

        if (pathInfo != null && pathInfo.matches("/\\d+/status")) {
            long id = Long.parseLong(pathInfo.split("/")[1]);
            updateStatus(id, request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL for POST request: " + pathInfo);
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
            System.err.println("Error fetching CreditRequest: " + e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.err.println("Full stack trace:");
            System.err.println(stackTrace);

            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Json.createObjectBuilder()
                    .add("error", "Error fetching status history")
                    .add("errorMessage", e.getMessage())
                    .add("stackTrace", stackTrace)
                    .build());
        }
    }

    private void updateStatus(long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            JsonReader jsonReader = Json.createReader(request.getReader());
            JsonObject jsonObject = jsonReader.readObject();
            String statusName = jsonObject.getString("name");
            String statusDescription = jsonObject.getString("description");
            String pivotDescription = jsonObject.getString("pivotDescription", "Status updated"); // Default value if not provided

            Optional<CreditRequest> optionalCreditRequest = creditRequestService.getCreditRequest(id);
            if (optionalCreditRequest.isPresent()) {
                CreditRequest creditRequest = optionalCreditRequest.get();
                CreditStatus newStatus = new CreditStatus(statusName, statusDescription);
                CreditRequestStatusHistory newStatusHistory = new CreditRequestStatusHistory(creditRequest, newStatus, LocalDateTime.now());
                newStatusHistory.setDescription(pivotDescription); // Set the description for the pivot table
                creditRequest.getStatusHistory().add(newStatusHistory);

                CreditRequest updatedRequest = creditRequestService.createCreditRequest(creditRequest);

                sendJsonResponse(response, HttpServletResponse.SC_OK, Json.createObjectBuilder()
                        .add("message", "Credit request updated with new status")
                        .add("newStatus", statusName)
                        .add("newStatusDescription", statusDescription)
                        .add("pivotDescription", pivotDescription)
                        .add("updatedCreditRequestId", updatedRequest.getId())
                        .build());
            } else {
                sendJsonResponse(response, HttpServletResponse.SC_NOT_FOUND, Json.createObjectBuilder()
                        .add("error", "Credit request not found")
                        .build());
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.err.println("Error updating status: " + e.getMessage());
            System.err.println("Full stack trace:");
            System.err.println(stackTrace);

            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Json.createObjectBuilder()
                    .add("error", "Error updating status")
                    .add("errorMessage", e.getMessage())
                    .add("stackTrace", stackTrace)
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
    }}


