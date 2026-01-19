package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.Customer;
import com.ey.entity.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByUser(User user);
}

