import com.asmaa.hariti.demo.dao.repositories.CreditStatusDAO;
import com.asmaa.hariti.demo.model.entities.CreditStatus;
import com.asmaa.hariti.demo.service.CreditStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreditStatusServiceTest {

    @Mock
    private CreditStatusDAO creditStatusDAO;

    @InjectMocks
    private CreditStatusService creditStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCreditStatus() {
        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setId(1L);
        when(creditStatusDAO.save(1L)).thenReturn(1L);

        Long result = creditStatusService.createCreditStatus(creditStatus);

        assertEquals(1L, result);
        verify(creditStatusDAO, times(1)).save(1L);
    }

    @Test
    void testDeleteCreditStatus() {
        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setId(1L);

        creditStatusService.deleteCreditStatus(creditStatus);

        verify(creditStatusDAO, times(1)).deleteCreditStatus(String.valueOf(1L));
    }
}