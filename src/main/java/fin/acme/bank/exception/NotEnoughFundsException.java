package fin.acme.bank.exception;

public class NotEnoughFundsException extends Exception {
    public NotEnoughFundsException(String message) {
        super(message);
    }
}