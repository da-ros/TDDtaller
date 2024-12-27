package fin.acme.bank.service.Imp;

import fin.acme.bank.exception.NotEnoughFundsException;
import fin.acme.bank.exception.NotFoundAccountException;
import fin.acme.bank.model.Account;
import fin.acme.bank.service.BankTransferService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BankTransferServiceImp implements BankTransferService {

    private BankTransferServiceImp bankTransferServiceImp;
    private Map<String, Account> accountData;

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount, String description)
            throws  NotFoundAccountException, NotEnoughFundsException {
        if (!accountData.containsKey(fromAccountNumber)) {
            throw new NotFoundAccountException("Not found fromAccount");
        }

        if (!accountData.containsKey(toAccountNumber)) {
            throw new NotFoundAccountException("Not found toAccount");
        }

        Account fromAccount = accountData.get(fromAccountNumber);
        Account toAccount = accountData.get(toAccountNumber);

        if (fromAccount.getBalance() < amount) {
            throw new NotEnoughFundsException("Amount is greather than available fromAccount's balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
    }

    public void  setAccountData(Map<String, Account> accountData) {
        this.accountData = accountData;
    }
}
