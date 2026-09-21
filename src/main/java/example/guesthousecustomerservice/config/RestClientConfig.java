package example.guesthousecustomerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder bookingServiceRestClientBuilder(
            @Value("${booking-service.base-url}") String baseUrl) {
        return RestClient.builder().baseUrl(baseUrl);
    }
}