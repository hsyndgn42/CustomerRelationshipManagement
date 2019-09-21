package com.hd.crm.crm.model.dao;

import com.hd.crm.crm.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
}
