package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Customer;
import com.ey.entity.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByUser(User user);
}
