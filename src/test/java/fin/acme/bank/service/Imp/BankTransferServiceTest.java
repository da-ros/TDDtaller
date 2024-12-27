package fin.acme.bank.service.Imp;
import fin.acme.bank.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;


public class BankTransferServiceTest {

    private BankTransferService transferService;
    private Map<String, Account> mockAccountData;

    @BeforeEach
    void setUp() {
        // Mock data setup
        mockAccountData = new HashMap<>();
        mockAccountData.put("123-456", new Account("123-456", "John Doe", 1000.0));
        mockAccountData.put("789-101", new Account("789-101", "Jane Smith", 500.0));

        transferService = BankTransferService.getInstance(mockAccountData);
    }

    @Test
    void testTransferSuccess() {
        // Arrange
        String fromAccount = "123-456";
        String toAccount = "789-101";
        double transferAmount = 500.0;
        String description = "Payment for services";

        // Act
        boolean result = transferService.transfer(fromAccount, toAccount, transferAmount, description);

        // Assert
        assertTrue(result, "The transfer should succeed");
        assertEquals(500.0, mockAccountData.get(fromAccount).getBalance(), "Sender's balance should decrease by 500");
        assertEquals(1000.0, mockAccountData.get(toAccount).getBalance(), "Recipient's balance should increase by 500");
    }




}
