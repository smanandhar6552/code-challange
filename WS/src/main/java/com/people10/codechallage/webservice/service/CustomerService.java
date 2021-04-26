package com.people10.codechallage.webservice.service;

import com.people10.codechallage.webservice.dto.CustomerDTO;
import com.people10.codechallage.webservice.entities.Customer;
import com.people10.codechallage.webservice.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomer(){
        List<Customer> customers =  (List<Customer>) customerRepository.findAll();
        return convertCustomerDtos(customers);
    }
    public void addCustomer(CustomerDTO customerdto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerdto, customer);
        customerRepository.save(customer);
    }
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
    public void updateCustomer(CustomerDTO customerdto, Long id){
        customerdto.setId(id);
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerdto, customer);
        customerRepository.save(customer);
    }

    public CustomerDTO getCustomerById(Long id) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer c = customerRepository.findById(id).get();
        BeanUtils.copyProperties(c, customerDTO);
        return customerDTO;
    }

    public List<CustomerDTO> searchCustomer(String firstName, String lastName, String email, String ip, String latitude, String longitude) {
        List<Customer> customers =  customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(firstName)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"),firstName)));
                }
                if(!StringUtils.isEmpty(lastName)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"),lastName)));
                }
                if(!StringUtils.isEmpty(email)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"),email)));
                }
                if(!StringUtils.isEmpty(ip)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("ip"),ip)));
                }
                if(!StringUtils.isEmpty(latitude)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("latitude"),latitude)));
                }
                if(!StringUtils.isEmpty(longitude)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("longitude"),longitude)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        return convertCustomerDtos(customers);
    }

    private List<CustomerDTO> convertCustomerDtos(List<Customer> customers) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer: customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer, customerDTO);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
}
