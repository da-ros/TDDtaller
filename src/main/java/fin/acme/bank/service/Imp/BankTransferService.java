package fin.acme.bank.service.Imp;

import fin.acme.bank.model.Account;

import java.util.Map;

public interface BankTransferService {

    BankTransferServiceImp getInstance(Map<String, Account> accountData);

    boolean transfer(String fromAccount, String toAccount, double amount, String description);
}
