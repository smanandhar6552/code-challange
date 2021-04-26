package com.people10.codechallage.etl.processor;

import com.people10.codechallage.etl.model.Customer;
import org.springframework.stereotype.Component;

import org.springframework.batch.item.ItemProcessor;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        return customer;
    }
}
