package fin.acme.bank.service.Imp;

import fin.acme.bank.exception.NotEnoughFundsException;
import fin.acme.bank.exception.NotFoundAccountException;
import fin.acme.bank.model.Account;

import java.util.Map;

public interface BankTransferService {

    void setAccountData(Map<String, Account> accountData);

    boolean transfer(String fromAccount, String toAccount, double amount, String description) throws NotFoundAccountException, NotEnoughFundsException;
}
