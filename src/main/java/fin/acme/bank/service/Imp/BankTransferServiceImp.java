package fin.acme.bank.service.Imp;

import fin.acme.bank.model.Account;

import java.util.Map;

public class BankTransferServiceImp implements BankTransferService {

    private BankTransferServiceImp bankTransferServiceImp;
    private Map<String, Account> accountData;

    public boolean transfer(String fromAccount, String toAccount, double amount, String description) {
        return false;
    }

    public BankTransferServiceImp getInstance(Map<String, Account> accountData) {
        if(bankTransferServiceImp == null){
            bankTransferServiceImp = new BankTransferServiceImp();
        }
        if(accountData == null){
            this.accountData = accountData;
        }

        return bankTransferServiceImp;

    }
}
