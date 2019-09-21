package com.hd.crm.crm.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMER")
@AttributeOverride(name = "id", column = @Column(name = "CUSTOMER_ID"))
public class Customer extends BaseEntity {
    private static final long serialVersionUID = 2907734039742952156L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "DATEOFBIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "PHONENUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL")
    @Email(message = "Wrong email.")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
