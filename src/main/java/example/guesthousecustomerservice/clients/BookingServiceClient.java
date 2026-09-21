package example.guesthousecustomerservice.clients;

import example.guesthousecustomerservice.exceptions.BookingServiceUnavailableException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class BookingServiceClient {

    private final RestClient restClient;

    public BookingServiceClient(RestClient.Builder bookingServiceRestClientBuilder) {
        this.restClient = bookingServiceRestClientBuilder.build();
    }

    public boolean hasActiveBookings(Long customerId) {
        try {
            Boolean result = restClient.get()
                    .uri("/api/bookings/customers/{customerId}/has-active", customerId)
                    .retrieve()
                    .body(Boolean.class);
            return Boolean.TRUE.equals(result);
        } catch (RestClientException e) {
            throw new BookingServiceUnavailableException(
                    "Could not reach booking service to check active bookings", e);
        }
    }
}