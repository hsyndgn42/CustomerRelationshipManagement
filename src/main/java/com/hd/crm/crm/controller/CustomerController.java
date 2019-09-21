package com.hd.crm.crm.controller;

import com.hd.crm.crm.common.ExceptionMessageBuilder;
import com.hd.crm.crm.model.dao.CustomerDAO;
import com.hd.crm.crm.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;

@RestController
public class CustomerController {

    private static final ExceptionMessageBuilder exceptionMessageBuilder = new ExceptionMessageBuilder();
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @PostMapping(value = "/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody @Valid Customer customer) {
        return customerDAO.save(customer);
    }

    @GetMapping(value = "/customers")
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return customerDAO.findAll(pageable);
    }

    @GetMapping(value = "/customers/{customerId}")
    public Customer findCustomerByCustomerId(@PathVariable Long customerId) {

        return customerDAO.findById(customerId).orElseThrow(() -> new
                ResourceAccessException(exceptionMessageBuilder.generateCustomerNotFoundMessage(customerId)));
    }

    @DeleteMapping(value = "customers/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long customerId) {

        return customerDAO.findById(customerId).map(customer -> {
                    customerDAO.delete(customer);
                    return ResponseEntity.ok(customer);
                }
        ).orElseThrow(() -> new ResourceAccessException(exceptionMessageBuilder.generateCustomerNotFoundMessage(customerId)));

    }

    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody @Valid Customer newCustomer) {
        return customerDAO.findById(customerId).map(customer -> {
            customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setEmail(newCustomer.getEmail());
            customer.setName(newCustomer.getName());
            customer.setSurname(newCustomer.getSurname());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
            customerDAO.save(customer);
            return ResponseEntity.ok(customer);
        }).orElseThrow(() -> new ResourceAccessException(exceptionMessageBuilder.generateCustomerNotFoundMessage(customerId)));
    }

}
