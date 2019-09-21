package com.hd.crm.crm.controller;

import com.hd.crm.crm.common.ExceptionMessageBuilder;
import com.hd.crm.crm.model.dao.AccountDAO;
import com.hd.crm.crm.model.dao.CustomerDAO;
import com.hd.crm.crm.model.entity.Account;
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
public class AccountController {

    private static final ExceptionMessageBuilder exceptionMessageBuilder = new ExceptionMessageBuilder();
    private AccountDAO accountDAO;
    private CustomerDAO customerDAO;

    @Autowired
    public AccountController(AccountDAO accountDAO, CustomerDAO customerDAO) {
        this.accountDAO = accountDAO;
        this.customerDAO = customerDAO;
    }

    @PostMapping(value = "/customers/{customerId}/accounts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account saveAccount(@PathVariable Long customerId, @RequestBody @Valid Account account) {
        return customerDAO.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            return accountDAO.save(account);
        }).orElseThrow(() -> new ResourceAccessException(exceptionMessageBuilder.generateCustomerNotFoundMessage(customerId)));
    }

    @GetMapping(value = "/customers/{customerId}/accounts")
    public Page<Account> getAllAccounts(@PathVariable Long customerId, Pageable pageable) {
        return accountDAO.findCustomerByCustomerId(customerId, pageable);
    }

    @DeleteMapping(value = "/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long customerId, @PathVariable Long accountId) {
        if (!(customerDAO.findById(customerId).isPresent())) {
            throw new ResourceAccessException(exceptionMessageBuilder.generateCustomerNotFoundMessage(customerId));
        }

        return accountDAO.findById(accountId).map(account -> {
            accountDAO.delete(account);
            return ResponseEntity.ok(account);
        }).orElseThrow(() -> new ResourceAccessException(exceptionMessageBuilder.generateAccountNotFoundMessage(accountId)));

    }

    @PutMapping(value = "/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long customerId, @PathVariable Long accountId,
                                                 @RequestBody @Valid Account newAccount) {

        Customer customer = customerDAO.findById(customerId).orElseThrow(() ->
                new ResourceAccessException(exceptionMessageBuilder.generateCustomerNotFoundMessage(customerId)));

        return accountDAO.findById(accountId).map(account -> {
            account.setCustomer(customer);
            account.setAccountName(newAccount.getAccountName());
            account.setAccountType(newAccount.getAccountType());
            account.setBalance(newAccount.getBalance());
            account.setOpeningDate(newAccount.getOpeningDate());
            accountDAO.save(account);
            return ResponseEntity.ok(account);

        }).orElseThrow(() -> new ResourceAccessException(exceptionMessageBuilder.generateAccountNotFoundMessage(accountId)));
    }
}
