package com.people10.codechallage.etl.services;

import com.people10.codechallage.etl.model.Customer;
import com.people10.codechallage.etl.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByfirstNameAndLastName(firstName, lastName);
    }
}
