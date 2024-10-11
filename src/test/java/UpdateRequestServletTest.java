import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.asmaa.hariti.demo.UpdateRequestServlet;
import com.asmaa.hariti.demo.model.entities.CreditRequest;
import com.asmaa.hariti.demo.model.entities.CreditRequestStatusHistory;
import com.asmaa.hariti.demo.service.CreditRequestService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UpdateRequestServletTest {

    @Mock
    private CreditRequestService creditRequestService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private UpdateRequestServlet servlet;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new UpdateRequestServlet(creditRequestService);
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void testUpdateStatus() throws Exception {
        long creditRequestId = 1L;
        String jsonInput = "{\"name\":\"APPROVED\",\"description\":\"Credit request approved\",\"pivotDescription\":\"Status changed to approved\"}";
        BufferedReader reader = new BufferedReader(new StringReader(jsonInput));
        when(request.getReader()).thenReturn(reader);
        when(request.getPathInfo()).thenReturn("/" + creditRequestId + "/status");

        CreditRequest mockCreditRequest = new CreditRequest();
        mockCreditRequest.setId(creditRequestId);
        mockCreditRequest.setStatusHistory(new ArrayList<>());
        when(creditRequestService.getCreditRequest(creditRequestId)).thenReturn(Optional.of(mockCreditRequest));
        when(creditRequestService.createCreditRequest(any(CreditRequest.class))).thenReturn(mockCreditRequest);

        servlet.doPost(request, response);


        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setStatus(HttpServletResponse.SC_OK);

        // Capture the JSON response
        String jsonResponse = stringWriter.toString();
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
        JsonObject responseJson = jsonReader.readObject();

        // Assert the response content
        assertEquals("Credit request updated with new status", responseJson.getString("message"));
        assertEquals("APPROVED", responseJson.getString("newStatus"));
        assertEquals("Credit request approved", responseJson.getString("newStatusDescription"));
        assertEquals("Status changed to approved", responseJson.getString("pivotDescription"));
        assertEquals(creditRequestId, responseJson.getJsonNumber("updatedCreditRequestId").longValue());

        // Verify that the credit request was updated
        ArgumentCaptor<CreditRequest> creditRequestCaptor = ArgumentCaptor.forClass(CreditRequest.class);
        verify(creditRequestService).createCreditRequest(creditRequestCaptor.capture());
        CreditRequest updatedCreditRequest = creditRequestCaptor.getValue();
        assertEquals(1, updatedCreditRequest.getStatusHistory().size());
        CreditRequestStatusHistory newStatus = updatedCreditRequest.getStatusHistory().get(0);
        assertEquals("APPROVED", newStatus.getStatus().getName());
        assertEquals("Status changed to approved", newStatus.getDescription());
    }
}