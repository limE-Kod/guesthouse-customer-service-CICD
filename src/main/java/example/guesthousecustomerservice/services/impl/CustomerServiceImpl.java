package example.guesthousecustomerservice.services.impl;


import example.guesthousecustomerservice.clients.BookingServiceClient;
import example.guesthousecustomerservice.dtos.CustomerDTO;
import example.guesthousecustomerservice.exceptions.CustomerHasActiveBookingsException;
import example.guesthousecustomerservice.models.Customer;
import example.guesthousecustomerservice.repositories.CustomerRepository;
import example.guesthousecustomerservice.services.CustomerService;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import java.util.List;


@Service


public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BookingServiceClient bookingServiceClient;


    public CustomerServiceImpl(CustomerRepository customerRepository, BookingServiceClient bookingServiceClient) {
        this.customerRepository = customerRepository;
        this.bookingServiceClient = bookingServiceClient;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> new CustomerDTO(c.getName(), c.getId()))
                .toList();
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow();
        return new CustomerDTO(customer.getName(), customer.getId());
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName());
        Customer saved = customerRepository.save(customer);
        return new CustomerDTO(saved.getName(), saved.getId());
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setName(customerDTO.getName());
        Customer saved = customerRepository.save(customer);
        return new CustomerDTO(saved.getName(), saved.getId());
    }

    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        if (bookingServiceClient.hasActiveBookings(id)) {
            throw new CustomerHasActiveBookingsException("Can't remove customer with active booking!");
        }
        customerRepository.deleteById(id);
    }
}



