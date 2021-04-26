package com.people10.codechallage.webservice;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.people10.codechallage.webservice.dto.CustomerDTO;
import com.people10.codechallage.webservice.entities.Customer;
import com.people10.codechallage.webservice.repository.CustomerRepository;
import com.people10.codechallage.webservice.service.CustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customerInDB;

    @Before
    public void setUp() {
        customerRepository.deleteAll();
        Customer c = new Customer();
        c.setFirstName("Nishant");
        c.setLastName("Shakya");
        c.setEmail("nish@gmail.com");
        c.setCreated_by("2020-01-26T05:06:00Z");
        c.setIp("192.168.1.1");
        customerInDB = customerRepository.save(c);
    }

    @After
    public void cleanUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void testFindAllCustomers() throws Exception {
        List<CustomerDTO> customerDTO = customerService.getAllCustomer();
        Assert.assertEquals("size expected is 1", 1, customerDTO.size());
        Assert.assertEquals("failure - expected name is Nishant", "Nishant", customerDTO.get(0).getFirstName());
    }

    @Test
    public void testAddCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Emp 1");
        customerDTO.setLastName("Emp LastName 1");
        customerDTO.setEmail("nish@gmail.com");
        customerDTO.setCreated_at("2020-01-26T05:06:00Z");
        customerDTO.setIp("192.168.1.1");
        customerService.addCustomer(customerDTO);

        List<CustomerDTO> customerDTOs = customerService.getAllCustomer();
        Assert.assertEquals("size expected is 1", 2, customerDTOs.size());
    }

    @Test
    public void testDeleteCustomer() throws  Exception{
        customerService.deleteCustomer(customerInDB.getId());
        List<CustomerDTO> customerDTO = customerService.getAllCustomer();
        Assert.assertEquals("expected size should be 0",0, customerDTO.size());
    }

    @Test
    public void updateCustomer() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("New emp1");
        customerDTO.setLastName("new emp last name");
        customerDTO.setEmail("nish@gmail.com");
        customerDTO.setCreated_at("2020-01-27T05:06:00Z");
        customerDTO.setIp("192.168.1.1");
        customerService.updateCustomer(customerDTO, customerInDB.getId());
        CustomerDTO updateCustomer = customerService.getCustomerById(customerInDB.getId());
        Assert.assertEquals("failure - firstname should be updated as New emp1", "New emp1", updateCustomer.getFirstName());
    }

    @Test
    public void searchCustomerReturningCustomers() throws Exception {
        List<CustomerDTO> customerDTOS = customerService.searchCustomer("Nishant", "Shakya", null, null, null, null);
        Assert.assertEquals("failure expected size of 1",1, customerDTOS.size());
        Assert.assertEquals("failure expected first name is Nishant","Nishant", customerDTOS.get(0).getFirstName());
    }


    @Test
    public void searchCustomerNonReturningCustomers() throws Exception {
        List<CustomerDTO> customerDTOS = customerService.searchCustomer("Nishant", "Test", null, null, null, null);
        Assert.assertEquals("failure expected size of 0",0, customerDTOS.size());
    }
    @Test
    public void getCustomerbyId() throws Exception{
        CustomerDTO customerDTO=customerService.getCustomerById(customerInDB.getId());
        Assert.assertEquals("failure displaying customer by Id","Nishant",customerDTO.getFirstName());
    }
}

