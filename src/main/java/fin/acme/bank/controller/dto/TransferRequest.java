package fin.acme.bank.controller.dto;

public record TransferRequest(String fromAccount, String toAccount, double amount, String description) {
}
