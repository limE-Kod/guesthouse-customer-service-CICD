package example.guesthousecustomerservice.clients;

import example.guesthousecustomerservice.exceptions.BookingServiceUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class BookingServiceClientTest {

    private MockRestServiceServer mockServer;
    private BookingServiceClient bookingServiceClient;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://booking-service-test");
        mockServer = MockRestServiceServer.bindTo(builder).build();
        bookingServiceClient = new BookingServiceClient(builder);
    }

    @Test
    void hasActiveBookings_returnsTrue_whenBookingServiceSaysSo() {

        mockServer.expect(requestTo("http://booking-service-test/api/bookings/customers/1/has-active"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON));

        assertTrue(bookingServiceClient.hasActiveBookings(1L));
    }

    @Test
    void hasActiveBookings_returnsFalse_whenNoActiveBookings() {

        mockServer.expect(requestTo("http://booking-service-test/api/bookings/customers/2/has-active"))
                .andRespond(withSuccess("false", MediaType.APPLICATION_JSON));

        assertFalse(bookingServiceClient.hasActiveBookings(2L));
    }

    @Test
    void hasActiveBookings_throwsUnavailable_whenBookingServiceErrors() {

        mockServer.expect(requestTo("http://booking-service-test/api/bookings/customers/3/has-active"))
                .andRespond(withServerError());

        assertThrows(BookingServiceUnavailableException.class,
                () -> bookingServiceClient.hasActiveBookings(3L));
    }
}