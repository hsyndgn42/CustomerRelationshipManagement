package com.hd.crm.crm.model.dao;

import com.hd.crm.crm.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account, Long> {

    Page<Account> findCustomerByCustomerId(Long customerId, Pageable pageable);
}
