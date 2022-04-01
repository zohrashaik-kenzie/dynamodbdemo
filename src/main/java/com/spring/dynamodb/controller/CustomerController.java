package com.spring.dynamodb.controller;


import com.spring.dynamodb.entity.Customer;
import com.spring.dynamodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

        @Autowired
        private CustomerRepository customerRepository;

    /**
     *
     * @param customer
     * @return the inserted customer, customerId
     *
     * @throws SQLDataException
     */
        @PostMapping("/add")
        public Customer saveCustomer(@RequestBody Customer customer) throws SQLDataException {
            return customerRepository.saveCustomer(customer);
        }

    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("/get/{id}")
        public Customer getCustomerById(@PathVariable("id") String customerId) {
            return customerRepository.getCustomerById(customerId);
        }

        @DeleteMapping("/delete/{id}")
        public String deleteCustomerById(@PathVariable("id") String customerId) {
            return customerRepository.deleteCustomerById(customerId);
        }
    }
