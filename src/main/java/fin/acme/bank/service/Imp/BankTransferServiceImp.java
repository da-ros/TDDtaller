package fin.acme.bank.service.Imp;

import fin.acme.bank.model.Account;

import java.util.Map;

public class BankTransferServiceImp implements BankTransferService {

    private BankTransferServiceImp bankTransferServiceImp;
    private Map<String, Account> accountData;

    public boolean transfer(String fromAccount, String toAccount, double amount, String description) {
        return false;
    }

    public void  setAccountData(Map<String, Account> accountData) {
        this.accountData = accountData;
    }
}
