package com.hd.crm.crm.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hd.crm.crm.model.enumerated.AccountType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNT")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID"))
public class Account extends BaseEntity {
    private static final long serialVersionUID = 6749318223237963919L;

    @Column(name = "ACCOUNT_NAME")
    @NotNull(message = "Account Name can not be null or empty")
    private String accountName;

    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Account Type can not be null or empty")
    private AccountType accountType;

    @Column(name = "BALANCE")
    @NotNull(message = "Balance can not be null or empty")
    @DecimalMin(value = "0.00", message = "balance can not be less than 0.00")
    @DecimalMax(value = "99999.99", message = "balance can not be greater than 99999.99")
    private Double balance;

    @NotNull(message = "Account opening date can not be null or empty")
    @Column(name = "OPENINGDATE")
    private LocalDate openingDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
