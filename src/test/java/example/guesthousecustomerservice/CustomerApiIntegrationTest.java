package example.guesthousecustomerservice;

import example.guesthousecustomerservice.dtos.CustomerDTO;
import example.guesthousecustomerservice.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class CustomerApiIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void registerCustomer_shouldReturn201AndSaveToDatabase() {
        CustomerDTO newCustomer = new CustomerDTO("Anna", null);

        ResponseEntity<CustomerDTO> response =
                restTemplate.postForEntity("/api/customers", newCustomer, CustomerDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Anna", response.getBody().getName());
        assertTrue(customerRepository.findById(response.getBody().getId()).isPresent());
    }

    @Test
    void registerCustomer_shouldReturn400_whenNameIsBlank() {
        CustomerDTO invalidCustomer = new CustomerDTO("", null);

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/customers", invalidCustomer, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}