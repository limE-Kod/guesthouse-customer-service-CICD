package example.guesthousecustomerservice.services;


import example.guesthousecustomerservice.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getById(Long id);
    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO update(Long id, CustomerDTO customerDTO);
    void delete(Long id);
}
