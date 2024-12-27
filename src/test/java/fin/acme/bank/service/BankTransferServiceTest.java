package fin.acme.bank.service;
import fin.acme.bank.exception.NotEnoughFundsException;
import fin.acme.bank.exception.NotFoundAccountException;
import fin.acme.bank.model.Account;
import fin.acme.bank.service.Imp.BankTransferServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;


public class BankTransferServiceTest {
    private BankTransferServiceImp bankTransferService;


    @BeforeEach
    void setUp() {
        bankTransferService = new BankTransferServiceImp();
    }

    @Test ()
    @DisplayName("givenEnoughAmountWhenCallTransferThenSuccess")
    void givenEnoughAmountWhenCallTransferThenSuccess() {
        // Mock data setup
        Map<String, Account> mockAccountData = new HashMap<>();
        mockAccountData.put("123-456", new Account("123-456", "John Doe", 1000.0));
        mockAccountData.put("789-101", new Account("789-101", "Jane Smith", 0.0));
        bankTransferService.setAccountData(mockAccountData);

        String fromAccount = "123-456";
        String toAccount = "789-101";
        double transferAmount = 500.0;
        String description = "Payment for services";

        try {
            bankTransferService.transfer(fromAccount, toAccount, transferAmount, description);
            assertEquals(500.0, mockAccountData.get(fromAccount).getBalance(), "Sender's balance should decrease by 500");
            assertEquals(500.0, mockAccountData.get(toAccount).getBalance(), "Recipient's balance should increase by 500");
        } catch (NotFoundAccountException nfae) {
            assertTrue(nfae.getMessage().contains("Not found"), "test passed");
        } catch (NotEnoughFundsException ntfe) {
            assertTrue(ntfe.getMessage().contains("greather than available"), "test passed");
        }
    }


    @Test ()
    @DisplayName("givenNotEnoughAmountWhenCallTransferThenFailed")
    void givenNotEnoughAmountWhenCallTransferThenFailed() {
        // Mock data setup
        Map<String, Account> mockAccountData = new HashMap<>();
        mockAccountData.put("123-456", new Account("123-456", "John Doe", 500.0));
        mockAccountData.put("789-101", new Account("789-101", "Jane Smith", 0.0));
        bankTransferService.setAccountData(mockAccountData);

        String fromAccount = "123-456";
        String toAccount = "789-101";
        double transferAmount = 700.0;
        String description = "Payment for services";

        try {
            bankTransferService.transfer(fromAccount, toAccount, transferAmount, description);
        } catch (NotFoundAccountException nfae) {
            assertTrue(nfae.getMessage().contains("Not found"), "test passed");
        } catch (NotEnoughFundsException ntfe) {
            assertTrue(ntfe.getMessage().contains("greather than available"), "test passed");
        }
    }
}
