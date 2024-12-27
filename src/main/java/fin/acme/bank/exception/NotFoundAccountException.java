package fin.acme.bank.exception;

public class NotFoundAccountException extends Exception {
    public NotFoundAccountException(String message) {
        super(message);
    }
}
