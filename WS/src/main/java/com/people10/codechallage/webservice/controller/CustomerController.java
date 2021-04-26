package com.people10.codechallage.webservice.controller;

import com.people10.codechallage.webservice.dto.CustomerDTO;
import com.people10.codechallage.webservice.entities.Customer;
import com.people10.codechallage.webservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDTO> allCustomer() {
        return customerService.getAllCustomer();
    }
    @GetMapping("/customer/{id}")
    public CustomerDTO customerById(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody CustomerDTO customerdto){
        customerService.addCustomer(customerdto);
    }
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
    }
    @PutMapping("/customer/{id}")
    public void updateCustomer(@RequestBody CustomerDTO customerdto, @PathVariable("id") Long id){
        customerService.updateCustomer(customerdto, id);
    }

    @GetMapping("/search")
    public List<CustomerDTO> search(@RequestParam(value = "firstName", required = false) String firstName,
                       @RequestParam(value = "last_name", required = false) String lastName,
                       @RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "ip", required = false) String ip,
                       @RequestParam(value = "latitude", required = false) String latitude,
                       @RequestParam(value = "longitude", required = false) String longitude) {
        return customerService.searchCustomer(firstName,lastName, email, ip, latitude, longitude);

    }
}