package example.guesthousecustomerservice.exceptions;

public class CustomerHasActiveBookingsException extends RuntimeException {
    public CustomerHasActiveBookingsException(String message) {
        super(message);
    }
}