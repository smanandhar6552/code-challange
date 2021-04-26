package com.people10.codechallage.etl.writer;

import com.people10.codechallage.etl.model.Customer;
import com.people10.codechallage.etl.repositories.CustomerRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWriter implements ItemWriter<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customer> customers) throws Exception {
        customerRepository.saveAll(customers);
    }
}
