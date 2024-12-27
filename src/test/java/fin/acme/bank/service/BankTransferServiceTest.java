package fin.acme.bank.service;
import fin.acme.bank.model.Account;
import fin.acme.bank.service.Imp.BankTransferService;
import fin.acme.bank.service.Imp.BankTransferServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;


public class BankTransferServiceTest {
    private BankTransferServiceImp transferService;


    @BeforeEach
    void setUp() {
        transferService = new BankTransferServiceImp();
    }

    @Test ()
    @DisplayName("whenEnoughAmountThenTransferSuccess")
    void testTransferSuccess() {
        // Mock data setup
        Map<String, Account> mockAccountData = new HashMap<>();
        mockAccountData.put("123-456", new Account("123-456", "John Doe", 1000.0));
        mockAccountData.put("789-101", new Account("789-101", "Jane Smith", 0.0));
        transferService.setAccountData(mockAccountData);


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
        assertEquals(500.0, mockAccountData.get(toAccount).getBalance(), "Recipient's balance should increase by 500");
    }
}
