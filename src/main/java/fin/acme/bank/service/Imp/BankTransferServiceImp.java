package fin.acme.bank.service.Imp;

import fin.acme.bank.model.Account;

import java.util.Map;

public class BankTransferServiceImp implements BankTransferService {

    private BankTransferServiceImp bankTransferServiceImp;
    private Map<String, Account> accountData;

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount, String description) {
        if (!accountData.containsKey(fromAccountNumber) || !accountData.containsKey(toAccountNumber)) {
            return false;
        }

        Account fromAccount = accountData.get(fromAccountNumber);
        Account toAccount = accountData.get(toAccountNumber);

        if (fromAccount.getBalance() < amount) {
            return false; // Fondos insuficientes
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        return true;
    }

    public void  setAccountData(Map<String, Account> accountData) {
        this.accountData = accountData;
    }
}
