package com.people10.codechallage.etl;

import com.people10.codechallage.etl.model.Customer;
import com.people10.codechallage.etl.services.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testByFirstNameAndLastName() throws Exception {
        Customer customer = customerService.getCustomerByFirstNameAndLastName("Albert","Carter");
        Assert.assertNotNull(customer);
        Assert.assertEquals("failed - the ip address doesnot match", "acarter0@opera.com", customer.getEmail());
    }
}
