package com.people10.codechallage.etl.repositories;

import com.people10.codechallage.etl.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByfirstNameAndLastName(String firstName, String LastName);
}
