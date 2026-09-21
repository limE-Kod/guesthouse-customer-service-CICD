
package example.guesthousecustomerservice.exceptions;

public class BookingServiceUnavailableException extends RuntimeException {
    public BookingServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}