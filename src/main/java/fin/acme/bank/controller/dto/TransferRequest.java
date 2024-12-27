package fin.acme.bank.controller.dto;

import fin.acme.bank.model.Account;

import java.util.Map;

public record TransferRequest(String fromAccount, String toAccount, double amount, String description) {
}
